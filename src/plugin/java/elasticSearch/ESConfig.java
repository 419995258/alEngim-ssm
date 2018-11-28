/**
 * Project Name:alEngin
 * File Name:ESConfig.java
 * Package Name:elasticSearch
 * Date:2018年2月24日上午10:54:24
 * Copyright (c) 2018, wangzhen1984815@163.com All Rights Reserved.
 *
*/

package elasticSearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * ClassName:ESConfig <br/>
 * Date: 2018年2月24日 上午10:54:24 <br/>
 * 
 * @author wangzhen
 * @version
 * @since JDK 1.7
 * @see
 */
@Configuration
@ComponentScan(basePackageClasses = ESClientSpringFactory.class)
public class ESConfig {
	private static Logger logger = LoggerFactory.getLogger(ESConfig.class);

	public ESConfig() {
		logger.info("------------------------ESConfig容器启动初始化-----------------------");
	}

	@Value("${esclient.host}")
	private String host;

	@Value("${esclient.port}")
	private int port;

	@Value("${esclient.schema}")
	private String schema;

	@Value("${esclient.connectNum}")
	private Integer connectNum;

	@Value("${esclient.connectPerRoute}")
	private Integer connectPerRoute;

	@Bean
	public HttpHost httpHost() {
		return new HttpHost(host, port, schema);
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public ESClientSpringFactory getFactory() {
		return ESClientSpringFactory.build(httpHost(), connectNum, connectPerRoute);
	}

	@Bean
	@Scope("singleton")
	public RestClient getRestClient() {
		return getFactory().getClient();
	}

	@Bean
	@Scope("singleton")
	public RestHighLevelClient getRHLClient() {
		return getFactory().getRhlClient();
	}
}
