<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<title>文件上传</title>
</head>
<body>
	<h1>Please upload a file</h1>
	<form method="post" action="form.do" enctype="multipart/form-data">
		<input type="text" name="name" /> <br/>
		<input type="file" name="file" /> <br/>
		<input
			type="submit" />
	</form>
</body>
</html>