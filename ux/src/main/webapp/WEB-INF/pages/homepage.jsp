<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Household tracker</title>
    <!-- Bootstrap core CSS -->
    <link href="css/vendor/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/login_account.css" rel="stylesheet">
</head>
<body>
<div class = "container" >
<h1>Welcome!</h1>
<button id="logout">Logout</button>
</div> <!--/container-->
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/vendor/bootstrap.min.js"></script>
<script type="text/javascript">
    $( function() {
        $("#logout").click(function (event) {

            var posting = $.post("account/logout");

            posting.done( function() {
               window.location.href = "account/login_account.html";
            });
        });


    });
</script>
</body>
</html>