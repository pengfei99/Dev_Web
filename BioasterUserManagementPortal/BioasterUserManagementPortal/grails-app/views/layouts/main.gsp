<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Bioaster"/>
    </title>

    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>


    <g:layoutHead/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
    .navbar-default {
        background-color: #ff6600;
        border-color: #e7d839;
    }
    /* title */
    .navbar-default .navbar-brand {
        color: #777;
    }
    .navbar-default .navbar-brand:hover,
    .navbar-default .navbar-brand:focus {
        color: #5E5E5E;
    }
    /* link */
    .navbar-default .navbar-nav > li > a {
        color: #52772e;
    }
    .navbar-default .navbar-nav > li > a:hover,
    .navbar-default .navbar-nav > li > a:focus {
        color: #333;
    }
    .navbar-default .navbar-nav > .active > a,
    .navbar-default .navbar-nav > .active > a:hover,
    .navbar-default .navbar-nav > .active > a:focus {
        color: #555;
        background-color: #34b2e7;
    }
    .navbar-default .navbar-nav > .open > a,
    .navbar-default .navbar-nav > .open > a:hover,
    .navbar-default .navbar-nav > .open > a:focus {
        color: #555;
        background-color: #D5D5D5;
    }
    /* caret */
    .navbar-default .navbar-nav > .dropdown > a .caret {
        border-top-color: #777;
        border-bottom-color: #777;
    }
    .navbar-default .navbar-nav > .dropdown > a:hover .caret,
    .navbar-default .navbar-nav > .dropdown > a:focus .caret {
        border-top-color: #333;
        border-bottom-color: #333;
    }
    .navbar-default .navbar-nav > .open > a .caret,
    .navbar-default .navbar-nav > .open > a:hover .caret,
    .navbar-default .navbar-nav > .open > a:focus .caret {
        border-top-color: #555;
        border-bottom-color: #555;
    }
    /* mobile version */
    .navbar-default .navbar-toggle {
        border-color: #ddd059;
    }
    .navbar-default .navbar-toggle:hover,
    .navbar-default .navbar-toggle:focus {
        background-color: #DDD;
    }
    .navbar-default .navbar-toggle .icon-bar {
        background-color: #CCC;
    }
    @media (max-width: 767px) {
        .navbar-default .navbar-nav .open .dropdown-menu > li > a {
            color: #777;
        }

        .navbar-default .navbar-nav .open .dropdown-menu > li > a:hover,
        .navbar-default .navbar-nav .open .dropdown-menu > li > a:focus {
            color: #333;
        }
    }
    </style>

</head>
<body>

<div class="container-fluid">
    <g:render template="/layouts/header"/>
</div>

<div class="container">
    <g:layoutBody/>
</div>

<hr>
<div class="container"><g:render template="/layouts/footer"/></div>


</body>
</html>

