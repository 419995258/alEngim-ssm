package alEngin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zyyd.base.util.ObjectIDFactory;
import org.zyyd.engin.entity.AlEnginTrackTree;
import org.zyyd.engin.service.AlEnginTrackTreeService;

public class AlEnginTrackTreeServiceTest extends BaseJunitTest {

	private static Logger logger = LoggerFactory.getLogger(AlEnginTrackTreeServiceTest.class);

	@Autowired
	private AlEnginTrackTreeService alEnginTrackTreeService;

	@Test
	public void insertAlEnginTrackTreeData() {
		
		String taskId = "taskId1";
		int j=1;
		while(j<=10) {
			//10个人
			String userId = "userId"+j;
			List<AlEnginTrackTree> list = new ArrayList<AlEnginTrackTree>();
			int i=1;
			while(i<=10) {
				//10道题
				String nId=ObjectIDFactory.getGuid();
				String itpId="itpId"+i;
				String nodeId="nodeId"+i;
				AlEnginTrackTree alEnginTrackTree=new AlEnginTrackTree();
				alEnginTrackTree.setAnswerCode("");
				alEnginTrackTree.setAnswerTime(new Date());
				alEnginTrackTree.setAnswerTimespan(1200L);
				alEnginTrackTree.setBrowsSolResponse("");
				alEnginTrackTree.setBrowsSolRpsTime(new Date());
				alEnginTrackTree.setDomainId("zyyd");
				alEnginTrackTree.setIsCorrect(1);
				alEnginTrackTree.setItemType("");
				alEnginTrackTree.setItpId(itpId);
				alEnginTrackTree.setNodeId(nodeId);
				alEnginTrackTree.setParentId("-1");
				alEnginTrackTree.setSessionId("s1");
				alEnginTrackTree.setTrackStatus(2);
				alEnginTrackTree.setnId(nId);
				alEnginTrackTree.setUserId(userId);
				alEnginTrackTree.settId(taskId);
				list.add(alEnginTrackTree);
				i++;
			}
			j++;
			
			alEnginTrackTreeService.insertAlEnginTrackTreeData(userId, taskId, list);
		}


		
	}
}
