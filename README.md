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
	
###	Thymeleaf layout 布局
	
	<!-- 有这么一个html段 -->
	<footer th:fragment="copy"> 
	  © 2011 The Good Thymes Virtual Grocery
	</footer>
	
	<!-- 引用html段 -->
	<body>
	  <div th:insert="footer :: copy"></div>
	  <div th:replace="footer :: copy"></div>
	  <div th:include="footer :: copy"></div>
	</body>
	
	<!-- 最终结果 -->
	<body>
	  <!-- th:insert，div tag内部插入html段 -->
	  <div>
	    <footer>
	      © 2011 The Good Thymes Virtual Grocery
	    </footer>
	  </div>
	  <!-- th:replace，使用html段直接替换 div tag -->
	  <footer>
	    © 2011 The Good Thymes Virtual Grocery
	  </footer>
	  <!-- th:include，div tag内部插入html段（仅保留段子元素的内容） -->
	  <!-- 仔细对比 th:insert 与 th:include的结果 -->
	  <div>
	    © 2011 The Good Thymes Virtual Grocery
	  </div>
	</body>
	
	
	
	