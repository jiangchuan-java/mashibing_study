local value = redis.call("get", KEYS[1])  
if value == ARGV[1]
then 
	redis.call("set", KEYS[1], ARGV[2])
	return 1
else 
	return 0 
end 
