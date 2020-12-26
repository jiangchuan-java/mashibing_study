package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;


/**
 * @Author: jiangchuan
 * <p>
 * @Date: 20-12-4
 */
public class Demo_01 {

    @Test
    public void demo() throws Exception{
        Analyzer analyzer = new StandardAnalyzer();

        Path indexPath = Files.createTempDirectory("tempIndex");

        Directory directory = FSDirectory.open(indexPath);

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter iwriter = new IndexWriter(directory, config);

        Document doc = new Document();

        String text = "This is the jiangchuan to be indexed.";

        doc.add(new Field("weMediaDesc", text, TextField.TYPE_STORED));

        iwriter.addDocument(doc);

        iwriter.close();

        DirectoryReader ireader = DirectoryReader.open(directory);

        IndexSearcher isearcher = new IndexSearcher(ireader);

        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("weMediaDesc", analyzer);

        Query query = parser.parse("jiangchuan");

        ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;

        System.out.println(hits.length);

        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println(hitDoc.get("weMediaDesc"));
        }
        ireader.close();
        directory.close();
        IOUtils.rm(indexPath);
    }
}
