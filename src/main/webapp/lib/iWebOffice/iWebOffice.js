

	//��ʾ�ؼ���������ȡ��ʱ����
	function showWebOffice(obj){
		if($("#" + obj).length>0){//����ؼ����ڣ������ؿؼ�
    		$("#" + obj).attr("width","100%");
    	}
	}
	
	//���ؿؼ����������ʱ����
	function hideWebOffice(obj){
		if($("#" + obj).length>0){//����ؼ����ڣ������ؿؼ�
    		$("#" + obj).attr("width",0);
    	}
	}