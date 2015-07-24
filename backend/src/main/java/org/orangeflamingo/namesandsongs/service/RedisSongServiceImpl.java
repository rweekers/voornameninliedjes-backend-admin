package org.orangeflamingo.namesandsongs.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RedisSongServiceImpl implements RedisSongService {

	private static final Logger LOGGER = Logger
			.getLogger(RedisSongServiceImpl.class);
	
	// inject the actual template
    @Autowired
    private RedisTemplate<String, String> template;
    
    // inject the template as ListOperations
    // can also inject as Value, Set, ZSet, and HashOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String list, String value) {
        LOGGER.info("Adding link for key " + list + " and value " + value);
    	listOps.leftPush(list, value);
    }
}
