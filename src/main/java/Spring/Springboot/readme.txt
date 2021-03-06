Spring扩展点：其核心实现类是ConfigurationClassPostProcessor   
在它的postProcessBeanDefinitionRegistry()方法中解析了以下注解   
* @Configuration;
* @ComponentScacn;
* @Component;
* @Import
* @ImportResource;
---
* 注解类的扩展点
   1. @Configuration + @Bean
   2. @Import(引入接口扩展点)
   3. @Component引入一个普通的组件

* 接口类的扩展点，需配合@Import使用
   1. ImportSelector： 
   2. ImportBeanDefinitionRegistrar

* postProcessor扩展点
   1.BeanFactoryPostProcessor
   2.InstantiationAwareBeanPostProcessor -> aop
   3.BeanPostProcessor
   
   ```
   ImportSelector:
    @Override
             public String[] selectImports(AnnotationMetadata importingClassMetadata) {
                 return new String[] {HelloServiceA.class.getName(), HelloServiceB.class.getName()};
             }
   ```
   ```  
   ImportBeanDefinitionRegistrar:
   public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata
                                       ,BeanDefinitionRegistry registry);

   ```

   ```
   	private static final Set<String> candidateIndicators = new HashSet<String>(4); //传给spring当做source的类是否可被解析，要看是否含有下面的注解之一

   	static {
   		candidateIndicators.add(Component.class.getName());
   		candidateIndicators.add(ComponentScan.class.getName());
   		candidateIndicators.add(Import.class.getName());
   		candidateIndicators.add(ImportResource.class.getName());
   	}
   	```