<!DOCTYPE html>
<html lang="en" >
<head></head>
<body>
	<#list cityList as city>
		City: ${city.cityName} <br/>
		Desc: ${city.description!}<br/>
	</#list>
	
</body>
</html>