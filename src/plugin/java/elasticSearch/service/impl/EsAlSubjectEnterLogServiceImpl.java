package elasticSearch.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import elasticSearch.dao.EsAlSubjectEnterLogDao;
import elasticSearch.document.AlSubjectEnterLogDocument;
import elasticSearch.service.EsAlSubjectEnterLogService;

@Service
public class EsAlSubjectEnterLogServiceImpl implements EsAlSubjectEnterLogService {

	private static Logger logger = LoggerFactory.getLogger(EsAlSubjectEnterLogServiceImpl.class);
	
	@Autowired
	private EsAlSubjectEnterLogDao esAlSubjectEnterLogDao;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public AlSubjectEnterLogDocument findById(String id) {
		Optional<AlSubjectEnterLogDocument> opt = esAlSubjectEnterLogDao.findById(id);
		AlSubjectEnterLogDocument alSubjectEnterLogDocument = opt.get();
		return alSubjectEnterLogDocument;
	}

	@Override
	public void save(AlSubjectEnterLogDocument AlSubjectEnterLogDocument) {
		esAlSubjectEnterLogDao.save(AlSubjectEnterLogDocument);
	}

	@Override
	public List<AlSubjectEnterLogDocument> findBySessionId(String sessionId) {
		List<AlSubjectEnterLogDocument> list = esAlSubjectEnterLogDao.findBySessionId(sessionId);
		return list;
	}

	@Override
	public Long countField(String field,String value) {
		NativeSearchQueryBuilder searchQuery=new NativeSearchQueryBuilder();
		
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		queryBuilder.must(QueryBuilders.termQuery(field,value.toLowerCase()));
		
		searchQuery.withIndices(AlSubjectEnterLogDocument.INDEX_NAME).withTypes(AlSubjectEnterLogDocument.TYPE).withQuery(queryBuilder);
		
		Long count=elasticsearchTemplate.count(searchQuery.build());
		
		return count;
	}


	@Override
	public Map<String,Long> groupByField(String field,String value,String groupField) {
		NativeSearchQueryBuilder searchQuery=new NativeSearchQueryBuilder();
		
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		queryBuilder.must(QueryBuilders.termQuery(field,value.toLowerCase()));
		
		TermsAggregationBuilder termsAggregation = AggregationBuilders.terms("group_"+groupField).field(groupField+".keyword").order(BucketOrder.count(true));
		
		searchQuery.withIndices(AlSubjectEnterLogDocument.INDEX_NAME).withTypes(AlSubjectEnterLogDocument.TYPE).withQuery(queryBuilder).addAggregation(termsAggregation);
		
		Aggregations aggregations=elasticsearchTemplate.query(searchQuery.build(), new ResultsExtractor<Aggregations>() {
			@Override
			public Aggregations extract(SearchResponse response) {
				return response.getAggregations();
			}});
		
		Terms term =aggregations.get("group_"+groupField);

		 Map<String,Long> map = new HashMap<String,Long>();
		if(term.getBuckets().size()>0){
			for (Bucket bk : term.getBuckets()) {
                long count = bk.getDocCount();
                map.put(bk.getKeyAsString(), count);
            }
		}
		
		return map;
	}

	@Override
	public Map<String,Long> dateHistogram(String field,String value,String groupField) {
		NativeSearchQueryBuilder searchQuery=new NativeSearchQueryBuilder();
		
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		queryBuilder.must(QueryBuilders.termQuery(field,value.toLowerCase()));
		DateHistogramAggregationBuilder db=AggregationBuilders.dateHistogram("date_"+"creTime").field("creTime").dateHistogramInterval(DateHistogramInterval.MINUTE);
		db.subAggregation(AggregationBuilders.count("group_"+groupField).field(groupField+".keyword"));
		
		searchQuery.withIndices(AlSubjectEnterLogDocument.INDEX_NAME).withTypes(AlSubjectEnterLogDocument.TYPE).withQuery(queryBuilder).addAggregation(db);

		Aggregations aggregations=elasticsearchTemplate.query(searchQuery.build(), new ResultsExtractor<Aggregations>() {
			@Override
			public Aggregations extract(SearchResponse response) {
				return response.getAggregations();
			}});
		
		logger.info(aggregations.asMap().toString());
		Histogram term =aggregations.get("date_"+"creTime");

		 Map<String,Long> map = new HashMap<String,Long>();
		if(term.getBuckets().size()>0){
			for (org.elasticsearch.search.aggregations.bucket.histogram.Histogram.Bucket bk : term.getBuckets()) {
                long count = bk.getDocCount();
                map.put(bk.getKeyAsString(), count);
            }
		}
		
		return map;
	}

	@Override
	public void deleteById(String id) {
		esAlSubjectEnterLogDao.deleteById(id);
	}

}
