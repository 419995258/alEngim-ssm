package elasticSearch.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = AlSubjectEnterLogDocument.INDEX_NAME, type = AlSubjectEnterLogDocument.TYPE)
public class AlSubjectEnterLogDocument implements Serializable {

	public static final String INDEX_NAME = "al_subject_enter_log";

	public static final String TYPE = "LogDoc";

	@Id
	private String id;

	/**
	* 
	*/
	@ApiModelProperty(value = "")
	private String sessionId;

	/**
	 * 学科代码
	 */
	@ApiModelProperty(value = "学科代码")
	private String subjectCode;

	/**
	 * 域id
	 */
	@ApiModelProperty(value = "域id")
	private String domainId;

	/**
	 * 登录用户id
	 */
	@ApiModelProperty(value = "登录用户id")
	private String userId;

	/**
	 * 发生时间，因为使用消息队列，所以记录时间可能不是发生时间，要用日志对象中的发生时间
	 */
	@ApiModelProperty(value = "发生时间，因为使用消息队列，所以记录时间可能不是发生时间，要用日志对象中的发生时间")
	private Date creTime;

	/**
	 * 登录ip
	 */
	@ApiModelProperty(value = "登录ip")
	private String ipAddress;

	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}