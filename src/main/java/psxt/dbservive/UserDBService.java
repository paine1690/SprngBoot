package psxt.dbservive;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.Data;
import psxt.globalInfo.ResponseCode;
import psxt.mapper.UserDBMapper;
import psxt.mode.ResponseMessage;
import psxt.mode.ScoreMessage;
import psxt.mode.User;

@Data
@Service
public class UserDBService {
	
	@Autowired
	private UserDBMapper userDBMapper;
	
	public User getUserByUserName(String userName){
		return userDBMapper.getUserByUserName(userName);
	}
	
	//获取指定身份的所有账号
	public List<User> getUsersByRole(int role){
		return userDBMapper.getUserByRole(role);
	}
	
	
	//修改指定id的userName和password
	public ResponseMessage changeNameOrPassword(int id, String userName, String password){
		ResponseMessage responseMessage = new ResponseMessage();
		if(userDBMapper.changeNameOrPassword(id, userName, password)){
			responseMessage.setCode(ResponseCode.SUCCESS.ordinal()); 
		}else{
			responseMessage.setCode(ResponseCode.FAILED.ordinal()); 
		}
		return responseMessage;
	}
	
	//修改指定id的userName和password和school
	public ResponseMessage changeNameOrPassworORSchool(int id, String userName, String password, String school){
		ResponseMessage responseMessage = new ResponseMessage();
		if(userDBMapper.changeNameOrPassworORSchool(id, userName, password, school)){
			responseMessage.setCode(ResponseCode.SUCCESS.ordinal()); 
		}else{
			responseMessage.setCode(ResponseCode.FAILED.ordinal()); 
		}
		return responseMessage;
	}
	
	//修改指定id的分组信息
	public boolean changeGroup(int id, int group){
		return userDBMapper.changeGroup(id, group);
	}
	
	
	//新增一个账户,指定用户名、密码、学校、身份
	public ResponseMessage addNewAccount(String userName, String password, String school, int role){
		ResponseMessage responseMessage = new ResponseMessage();
		if(userDBMapper.addNewAccount(userName, password, school, role)){
			responseMessage.setCode(ResponseCode.SUCCESS.ordinal()); 
		}else{
			responseMessage.setCode(ResponseCode.FAILED.ordinal()); 
		}
		return responseMessage;
	}
	
	/**
	 * 上传或者更新文件路径
	 * @param userId
	 * @param fileDir
	 * @return
	 */
	public ResponseMessage updateFile(int userId, String fileDir){
		ResponseMessage responseMessage = new ResponseMessage();
		if(userDBMapper.updateFileDirByUserId(fileDir, userId)){
			responseMessage.setCode(ResponseCode.SUCCESS.ordinal());
		}
		else {
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
		}
		return responseMessage;
	}
	
	/**
	 * 获取该分组的学校信息以及分数信息
	 * @param group
	 * @param userId
	 * @return
	 */
	public List<ScoreMessage> getProjectMessageListOfJudge(int group, int userId){
		System.err.println("group:"+group + " userId:"+userId);
		List<ScoreMessage> list = new ArrayList<>();
		list = userDBMapper.selectProjectMessageByGroupAndUser(group, userId);
		return list;
	}
	
	//获取一个组对应的所有学校信息
	public List<User> getSchoolGroupOfTeacher(int groupId){
		List<User> list;
		list = userDBMapper.getSchoolGroupOfTeacher(groupId);
		return list;
	}
	
	/**
	 * 返回一个专家已经评审过的学校的编号
	 * @return
	 */
	public List<Integer> getSchoolOfUnscoredByTeacher(int teacherId){
		List<Integer> list;
		list = userDBMapper.getSchoolOfUnscoredByTeacher(teacherId);
		return list;
	}
	
}
