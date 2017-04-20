# dbconn

This project is to demo how to integrate mysql+mybatis and mongo with spring-boot

### 模板用 thymeleaf
	
	1、在模板里使用js语法时遇到问题， && 不能正确的被解析 
	
	/*<![CDATA[*/
		if(a && b){
    	// ....
		}
	/*]]>*/


首尾增加这样的注释一样的声明，由于thymeleaf是采用xml解析的方式进行替换的，所以javascript中&这样的xml实体转义字符会被识别为转义，所以会出sax解析错误。 

##### js 中的绝对路径 

	2、<script th:inline="javascript">
	
		 var contextPath = /*[[@{/}]]*/ ''; 
	
	  </script>
	  
	  <meta name="ctx" th:content="${#httpServletRequest.getContextPath()}" />
	  
	  
	  <script th:inline="javascript">
		
		$(function(){
			/*首尾增加这样的注释一样的声明，由于thymeleaf是采用xml解析的方式进行替换的，
			 *所以javascript中<![CDATA[&]]>这样的xml实体转义字符会被识别为转义，所以会出sax解析错误
			 */
			 var contextPath = /*[[${#httpServletRequest.getContextPath()}]]*/ '';
			 var _ctx = $("meta[name='ctx']").attr("content");
			 var basePath = /*[[@{/}]]*/'';
			 var base_Path = [[@{/}]];
			 console.log(_ctx);
			 console.log(contextPath);
			 console.log(basePath);
			 console.log(base_Path);
			$("#commit").click(function(){
				var param = $('#trapTask').serialize();
				console.log(param);
				$.post(basePath+"task/addTask",param,function(rs){
					/*<![CDATA[*/
					if(rs && rs.status==200){
					/*]]>*/
						Utils.alert('保存成功');
					}else{
						Utils.alert('保存失败');
					}
					
				});
			});
		})
	
	</script>