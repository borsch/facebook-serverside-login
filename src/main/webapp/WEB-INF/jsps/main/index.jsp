<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<a href="https://www.facebook.com/v2.10/dialog/oauth?
client_id=799295533586280
&redirect_uri=https://facebook-login-test-borsch.herokuapp.com/facebook
&response_type=code%20token
&scope=public_profile,email">login</a>

<br />
<c:forEach var="pair" items="${map}">
    ${pair.key} -> ${pair.value}
    <Br />
</c:forEach>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    var hash = location.hash;
    if (hash.length) {
        var split = hash.split('&'),
            access_token = null;

        for (var i = 0; i < split.length; ++i) {
            if (split[i].indexOf('access_token=') === 0) {
                access_token = split[i].replace('access_token=', '');
            }
        }

        if (access_token) {
            $.ajax({
                url: 'https://graph.facebook.com/debug_token?input_token=' + access_token +
                '&access_token=EAALW9H5ZAl2gBANtD9qPZARUntI1YQm1d8YPGWnnptmTKIjyUnDs6pB3sZBne3cZBbpx7ZCuGaNAlpwpwh63XWrDFF5cZCyPbpZAyoNrVwe1UfBX6CoFA1gjY4x2cxZAPgyjh8dpcqceiHRIaDqSQyivHAVz7skZCyZCWVprtfBw66pD0O34VOuoctMCZCl35MV2uwZD',
                success: function(response) {
                    console.log(response);
                }
            })
        }
    }
</script>
</body>
</html>
