function postNewProject() {
	// 验证是否为空
//	var data = $("#mform").serializeArray();
//	console.log($("#mform").serializeArray());
//	for ( var index in data) {
//		var formItem = data[index];
//		if ((formItem.name == "category" && formItem.value == "----选择一级分类----")
//				|| (formItem.name == "subcategory" && formItem.value == "----选择二级分类----")) {
//			modalAlert("请选项目类别！");
//			return;
//		}
//		if (formItem.value == "") {
//			modalAlert("请检查字段不能为空！");
//			return;
//		}
//	}
	console.log($("#attachementId").val() + "hhhhhhh" );
	if($("#attachementId").val() == "") {
		modalAlert("请上传附件！");
		return;
	}
	// 获取userID
	console.log("userId:::" + userId);
	
	$.ajax({
		url:"/psxt/addnewproject",
		type:"POST",
		dataType:"json",
		data:{
			fileDir:$("#attachementId").val(),
			userId : userId
		},
		success:function(data) {
			//清空，附件ID
			$("#attachementId").val("");
			if(data.code == 0) {
//				alert(data.message);
				//重置表单
				resetForm();
				modalAlert('提交成功');
			}	
			else
				alert(data.message);
			
		},
		error:function() {
			alert("项目上传失败，请重新尝试！");
		}
	});
}

function resetForm() {
	removeAddProjectPage();
	setAddPrjctPage();
}