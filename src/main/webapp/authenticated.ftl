<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="node_modules/font-awesome/css/font-awesome.css" >
        <title>SSO Midianet</title>
        <style>
            .form {
                position: relative;
                z-index: 1;
                background: #FFFFFF;
                max-width: 380px;
                margin: 0 auto 100px;
                padding: 30px;
                border-top-left-radius: 3px;
                border-top-right-radius: 3px;
                border-bottom-left-radius: 3px;
                border-bottom-right-radius: 3px;
                text-align: center;
            }
            .form .thumbnail {
                background: #5cb6ef;
                width: 150px;
                height: 150px;
                margin: 0 auto 30px;
                padding: 50px 30px;
                border-top-left-radius: 100%;
                border-top-right-radius: 100%;
                border-bottom-left-radius: 100%;
                border-bottom-right-radius: 100%;
                box-sizing: border-box;
            }
            .form .thumbnail img {
                display: block;
                width: 100%;
            }
            .error{
                color: #ff2c2f;
            }
            .form input {
                outline: 0;
                background: #f2f2f2;
                width: 100%;
                border: 0;
                margin: 0 0 15px;
                padding: 15px;
                border-top-left-radius: 3px;
                border-top-right-radius: 3px;
                border-bottom-left-radius: 3px;
                border-bottom-right-radius: 3px;
                box-sizing: border-box;
                font-size: 14px;
            }
            .form button {
                outline: 0;
                background: #5cb6ef;
                width: 100%;
                border: 0;
                padding: 15px;
                border-top-left-radius: 3px;
                border-top-right-radius: 3px;
                border-bottom-left-radius: 3px;
                border-bottom-right-radius: 3px;
                color: #FFFFFF;
                font-size: 14px;
                -webkit-transition: all 0.3 ease;
                transition: all 0.3 ease;
                cursor: pointer;
            }
            .form .message {
                margin: 15px 0 0;
                color: #b3b3b3;
                font-size: 12px;
            }
            .container {
                position: relative;
                z-index: 1;
                max-width: 300px;
                margin: 0 auto;
            }
            .container:before, .container:after {
                content: "";
                display: block;
                clear: both;
            }
            .container .info {
                margin: 50px auto;
                text-align: center;
            }
            .container .info h1 {
                margin: 0 0 15px;
                padding: 0;
                font-size: 36px;
                font-weight: 300;
                color: #1a1a1a;
            }
            .container .info span {
                color: #4d4d4d;
                font-size: 12px;
            }
            .container .info span a {
                color: #000000;
                text-decoration: none;
            }

            body {
                background: #ccc;
                font-family: "Roboto", sans-serif;
                -webkit-font-smoothing: antialiased;
                -moz-osx-font-smoothing: grayscale;
            }
            body:before {
                content: "";
                position: fixed;
                top: 0;
                left: 0;
                display: block;
                background: rgba(255, 255, 255, 0.8);
                width: 100%;
                height: 100%;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="info">
                <h1>SSO - Midianet</h1>
            </div>
        </div>
        <div class="form">
            <div class="thumbnail"><img src="img/joinha.png"/></div>
            <div class="row">
                Você já esta logado.
            </div>
            <div class="row" style="margin-top: 30px">
                <a href="logout">
                    <div>
                        <img style="width: 50px; height: 50px;" src="img/sair.jpg"/>
                    </div>
                    <div>
                        Deslogar
                    </div>
                </a>
            </div>
        </div>
    </body>
</html>
<script src="node_modules/jquery/dist/jquery.js"></script>
<script src="node_modules/bootstrap/dist/js/bootstrap.js"></script>