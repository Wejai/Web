<html>
    <head>
        <title>login form</title>
        <style>
        @import url(https://fonts.googleapis.com/css?family=Droid+Sans:400,700);
@import url(https://fonts.googleapis.com/css?family=Cabin:700);
@import "//netdna.bootstrapcdn.com/font-awesome/3.0/css/font-awesome.css";

body {
    background: #eee;
}

#wrapper {
    -webkit-perspective: 800px;
    -moz-perspective: 800px;
    -o-perspective: 800px;
    perspective: 800px;
    -webkit-transition: -webkit-transform 1s;
    -moz-transition: -moz-transform 1s;
    -o-transition: -o-transform 1s;
    transition: transform 1s;
    -webkit-transform-style: preserve-3d;
    -moz-transform-style: preserve-3d;
    -o-transform-style: preserve-3d;
    transform-style: preserve-3d;
    margin: 100px auto;
    width: 300px;
    position: relative;
}

.box {
    background: #fff;
	  -webkit-box-sizing: border-box;
	  -moz-box-sizing: border-box;
	  box-sizing: border-box;
    -webkit-transform-style: preserve-3d;
    -webkit-backface-visibility: hidden;
    padding: 25px;
    width: 100%;
    position: absolute;
    top: 0;
    border-radius: 5px;
    box-shadow: 0 2px 1px #e9e9e9;
}

#login input[type="text"], #login input[type="password"], #login button, .back button {
    outline: none;
    -webkit-transition: all 0.3s ease-in-out;
    -moz-transition: all 0.3s ease-in-out;
    -ms-transition: all 0.3s ease-in-out;
    -o-transition: all 0.3s ease-in-out;
    transition: all 0.3s ease-in-out;
    background: #ecf2f4;
    color: #bbc0c2;
    border: none;
    -webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
    width: 100%;
    padding: 20px 25px;
    font-size: 16px;
    margin: 0 0 15px;
    font-family: droid sans;
    border-radius: 5px;
}

#login button, .back button {
    background: #8eb5e2;
    color: white;
    margin: 0;
    font-size: 24px;
    cursor: pointer;
}

#login button:hover {
    background: #9dc2ed;
}

#login button:active {
    background: #719bcc;
}

[placeholder]:focus::-webkit-input-placeholder {
    -webkit-transition: all 0.2s ease-in-out;
    -moz-transition: all 0.2s ease-in-out;
    -ms-transition: all 0.2s ease-in-out;
    -o-transition: all 0.2s ease-in-out;
    transition: all 0.2s ease-in-out;
    -webkit-transform: translate(120px, 0);
    -moz-transform: translate(120px, 0);
    -ms-transform: translate(120px, 0);
    -o-transform: translate(120px, 0);
    transform: translate(120px, 0);
    opacity: 0;
}

.flip .front {
    -webkit-transform: rotateY(180deg);
    -moz-transform: rotateY(180deg);
    right: 250px;
    top: 50px;
}

.flip .back {
    -webkit-transform: rotateX(0deg) rotateY(0deg);
    -moz-transform: rotateX(0deg) rotateY(0deg);
    z-index: 10;
    right: 0;
    top: 50px;
}

.front {
    -webkit-transform: rotateX(0deg) rotateY(0deg);
    -webkit-transform-style: preserve-3d;
    -webkit-backface-visibility: hidden;
    -moz-transform: rotateX(0deg) rotateY(0deg);
    -moz-transform-style: preserve-3d;
    -moz-backface-visibility: hidden;
    -o-transition: all .4s ease-in-out;
    -ms-transition: all .4s ease-in-out;
    -moz-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
  right:0;
}


.default {
    text-align: center;
    margin: 25px 0;
}

.default i {
    color: #f96145;
    font-size: 100px;
    margin: 0 0 10px;
    display: block;
}

.default h1 {
    font-family: Cabin;
    font-weight: 700;
    margin: 0;
    color: #aaaaaa;
}

.back {
    -webkit-transform: rotateY(-180deg);
    -webkit-transform-style: preserve-3d;
    -webkit-backface-visibility: hidden;
    -moz-transform: rotateY(-180deg);
    -moz-transform-style: preserve-3d;
    -moz-backface-visibility: hidden;
    -o-transition: all .4s ease-in-out;
    -ms-transition: all .4s ease-in-out;
    -moz-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
    right: 250px;
}

.back h2 {
    font-family: droid sans;
    color: #aaa;
    margin: 0;
}

.back img {
    border-radius: 5px;
    margin: 0 0 15px;
}

.back button {
    background: #f96145;
}

.back button:hover {
    background: #ff7a61;
}

.back button:active {
    background: #e7593f;
}

.back ul {
    color: #aaaaaa;
    padding: 0;
    margin: 0 0 15px;
}

.back ul li {
    cursor: pointer;
    border-bottom: 1px solid #ececec;
    list-style-type: none;
    font-family: Droid Sans;
    margin: 0 0 15px;
    font-size: 13px;
    padding: 0 0 15px;
}

.back ul li i {
    margin-right: 5px;
    color: #ccc;
}

.back ul li span {
    -o-transition: all .2s ease-in-out;
    -ms-transition: all .2s ease-in-out;
    -moz-transition: all .2s ease-in-out;
    -webkit-transition: all .2s ease-in-out;
    transition: all .2s ease-in-out;
    padding: 2px 5px;
    display: block;
    font-size: 12px;
    float: right;
    border: 1px solid #ececec;
    border-radius: 10px;
}

.back ul li:hover span {
    color: white;
    border-color: #444;
    background: #444;
}
        </style>
    </head>
    <body>  
<div id="wrapper">
<form id="login" action="login" method="post" class="front box">
  <div class="default"><img src='Logo.png'/><h1>Press login</h1></div>
<input type="text" name="userId" id="userId" placeholder="User Id" />
<input type="password" name="pass" id="pass" placeholder="password" />
<button type="submit" class="login"><i class="icon-ok"></i></button>
</form>
</div>
<script type="text/javascript">
$(".login").click(function() {
	  $("#wrapper").addClass('flip');
	});

	$(".logout").click(function() {
	  $("#wrapper").removeClass('flip');
	});
</script>
</body>
</html>
