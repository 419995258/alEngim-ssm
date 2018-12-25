package elasticSearch.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import elasticSearch.document.AlSubjectEnterLogDocument;

@Repository
public interface EsAlSubjectEnterLogDao extends ElasticsearchRepository<AlSubjectEnterLogDocument, String> {
	List<AlSubjectEnterLogDocument> findBySessionId(String sessionId);
}
