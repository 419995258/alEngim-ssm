package elasticSearch.service;

import java.util.List;
import java.util.Map;

import elasticSearch.document.AlSubjectEnterLogDocument;

public interface EsAlSubjectEnterLogService {
	
	AlSubjectEnterLogDocument findById(String id);

	void save(AlSubjectEnterLogDocument alSubjectEnterLogDocument);
	
	List<AlSubjectEnterLogDocument> findBySessionId(String sessionId);
	
	void deleteById(String id);
	
	//-----------------聚合查询------------------------
	
	/**
	* @Title countField  
	* @Description 统计某个字段的数量
	* @author wangzhen
	* @date 2018年12月13日 上午9:28:49
	* @return Double
	 */
	Long countField(String field,String value);
	
	/**
	* @Title groupByField  
	* @Description 按某个字段分组
	* @author wangzhen
	* @date 2018年12月13日 上午9:32:25
	* @param field
	* @return Object
	 */
	Map<String,Long> groupByField(String field,String value,String groupField);
	
	
	/**
	* @Title dateHistogram  
	* @Description 按日期间隔分组
	* @author wangzhen
	* @date 2018年12月13日 上午9:36:39
	* @param field
	* @return Object
	 */
	Map<String,Long> dateHistogram(String field,String value,String groupField);
	
	
}
