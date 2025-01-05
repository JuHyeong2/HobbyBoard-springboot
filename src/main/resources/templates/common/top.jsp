<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
    line-height: 1.6;
    color: #333;
}

a {
	text-decoration: none;
}

a:hover{
	font-weight: bold;
}

/* Header styles */
header {
    box-shadow: 5px 5px 20px #f3f3f3;
}

.header-top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 2.5rem 2rem 1rem;
    /* max-width: 1200px; */
    /* margin: 0 auto; */
    
}

.img {
    font-size: 1.5rem;
    font-weight: bold;
    color: #0066cc;
    margin-right: 30px;
}

.search-bar {
    display: flex;
}

.search-bar input {
    padding: 0.5rem;
    width: 300px;
    border: 2px solid #0066cc;
    border-radius: 4px;
}

.search-bar button {
    padding: 0.5rem 1rem;
    background-color: #0066cc;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.main-nav ul {
    display: flex;
    list-style: none;
    max-width: 1200px;
    margin: 0 auto;
    padding: 1rem 2rem 1rem 0;
}

.main-nav li {
    margin-right: 2rem;
}

.main-nav a {
    text-decoration: none;
    color: #333;
    font-weight: 500;
}
.top-nav{
	display: flex;
    list-style: none;
    max-width: 1200px;
    padding: 1rem 2rem;
/* 	flex-direction: column; */
}

.top-button {
    color: #1a1a1a;
    cursor: pointer;
}

.navigation {
    padding: 0 2rem;
    display: flex;
    margin: 0 auto;
    justify-content: space-between;
}
</style>

</head>
<body>
	<header>
		<div class="header-top">
            <h1>HAMO</h1>
            <div class="search-bar">
                <input type="text" placeholder="검색어를 입력하세요">
                <button type="submit">검색</button>
            </div>
        </div>
        <div class="navigation">
        	<nav class="main-nav">
            	<ul>
               	 	<li><a href="#">케테고리</a></li>
               	 	<li><a href="#">공지</a></li>
               	 	<li><a href="#">프로젝트 소개</a></li>
               	 	<li><a href="#"></a></li>
                	<li><a href="#"></a></li>
                	<li><a href="#"></a></li>
            	</ul>
        	</nav>
        	<nav class="top-nav">
				<a class="top-button" href="#" >로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<a class="top-button" href="#" >회원가입</a>
			</nav>
		</div>
    </header>
</body>
</html>