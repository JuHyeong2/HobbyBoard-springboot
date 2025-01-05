<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

main {
    max-width: 1200px;
    margin: 2rem auto;
/*     padding: 0 2rem; */
}

.notice-boards {
    margin-bottom: 3rem;
}


.board-slider {
/*     display: flex; */
    overflow-x: auto;
    /* gap: 1rem; */
    padding: 1rem 0;
    /* height: 500px; */
    width: 100%;
    padding: 1rem 0;
    justify-content: space-between;
    flex-direction: column;
    align-items: center;
}

.board-card {
    min-width: 250px;
    padding: 1rem;
    border: 1px solid #e5e5e5;
    border-radius: 8px;
/*     transition-duration: 0.3s; */
    box-shadow: 5px 5px 20px #f3f3f3;
}

.board-card-content {
	display: flex;
    justify-content: flex-start;	
}

.board-img {
    height: 100px;
    background-color: #f0f2ff;
    margin-bottom: 1rem;
    border-radius: 4px;
}

.board-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
    gap: 1.5rem;
}


.board-item:hover {
    transform: translateY(-10px);
    transition-duration: 0.3s;
}
.board-item {
    padding: 1rem;
    border: 1px solid #e5e5e5;
    border-radius: 8px;
    transition-duration: 0.3s;
    box-shadow: 5px 5px 20px #f3f3f3;
}

.board-info h3 {
    margin: 0.5rem 0;
    font-size: 1.1rem;
}

.board-name {
    color: #666;
    margin-bottom: 0.5rem;
}

.board-details {
    font-size: 0.9rem;
    color: #888;
}

.board-info {
    color: #888;
    font-size: 0.9rem;
}

.board-info p {
    margin-bottom: 0.5rem;
}

img {
	width: 100%;
	height: 100px;
	
}

.hero-banner {
    position: relative;
    height: 450px;
    background: linear-gradient(#f0f2ff, #feffed);
    color: #1a1a1a;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    padding: 2rem;
}

.banner-content {
    max-width: 1200px;
    margin: 0 auto;
    width: 100%;
}

.banner-content h1 {
    font-size: 2.5rem;
    margin-bottom: 0.5rem;
}

.view-more {
    background: #feffed;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    margin-top: 1rem;
    cursor: pointer;
}

.banner-navigation {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 1rem;
    margin-top: 2rem;
}

.dots {
    display: flex;
    gap: 0.5rem;
}

.dot {
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background: lightgray;
}

.dot.active {
    background: white;
}
</style>
</head>
<body>
주형이가 바꿈
Glass가 바꿈
	<jsp:include page="../common/top.jsp"/>

		<section class="hero-banner">
            <div class="banner-content">
                <h1>하모에서 하비모임!!~</h1>
                <p>내가 원하는 그 모임으로~</p>
                <button class="view-more"></button>
            </div>
            <div class="banner-navigation">
                <button class="prev">&lt;</button>
                <div class="dots">
                    <span class="dot active"></span>
                    <span class="dot"></span>
                    <span class="dot"></span>
                    <span class="dot"></span>
                    <span class="dot"></span>
                    <span class="dot"></span>
                </div>
                <button class="next">&gt;</button>
            </div>
        </section>
    <main>
		<h2>공지</h2>
         <section class="notice-boards">
            <div class="board-slider">
                <div class="board-card">
                    <h3>공지 제목</h3>
                    <p>25.01.01</p>
                </div>
<!--                 More board cards -->
                <div class="board-card">
                    <h3>공지 제목</h3>
                    <p>25.01.01</p>
                </div>
                
                <div class="board-card">
                <div class="board-card-content">
                    <h3>공지 제목</h3>&nbsp;&nbsp;&nbsp;&nbsp;
                    <span>2025년 1월 16일 부터 기능구현을 시작합니다.</span>
                </div>    
                    <p>25.01.01</p>
                </div>
            </div>  
        </section> 
		<h2>게시글</h2>
        <section class="board-grid">
            <div class="board-item">
                <div class="board-img"><img alt="board-img" src="image/park.jpg"></div>
                <div class="board-info">
                    <h3>게시글 제목</h3>
                    <p class="board-name">게시글 제목</p>
                    <p class="board-details">카테고리 | 지역 | 1/10</p>
                </div>
            </div>
            <!-- Repeated board-item divs -->
            
            <div class="board-item">
                <div class="board-img"><img alt="board-img" src="image/park.jpg"></div>
                <div class="board-info">
                    <h3>게시글 제목</h3>
                    <p class="board-name">게시글 제목</p>
                    <p class="board-details">카테고리 | 지역 | 1/10</p>
                </div>
            </div>
            
            <div class="board-item">
                <div class="board-img"><img alt="board-img" src="image/park.jpg"></div>
                <div class="board-info">
                    <h3>게시글 제목</h3>
                    <p class="board-name">게시글 제목</p>
                    <p class="board-details">카테고리 | 지역 | 1/10</p>
                </div>
            </div>
            <div class="board-item">
                <div class="board-img"><img alt="board-img" src="image/park.jpg"></div>
                <div class="board-info">
                    <h3>게시글 제목</h3>
                    <p class="board-name">게시글 제목</p>
                    <p class="board-details">카테고리 | 지역 | 1/10</p>
                </div>
            </div>
        </section>
        <br/><br/>
        <section class="board-grid">
            <div class="board-item">
                <div class="board-img"></div>
                <div class="board-info">
                    <h3>게시글 제목</h3>
                    <p class="board-name">게시글 제목</p>
                    <p class="board-details">카테고리 | 지역 | 1/10</p>
                </div>
            </div>
            <!-- Repeated board-item divs -->
            
            <div class="board-item">
                <div class="board-img"></div>
                <div class="board-info">
                    <h3>게시글 제목</h3>
                    <p class="board-name">게시글 제목</p>
                    <p class="board-details">카테고리 | 지역 | 1/10</p>
                </div>
            </div>
            
            <div class="board-item">
                <div class="board-img"></div>
                <div class="board-info">
                    <h3>게시글 제목</h3>
                    <p class="board-name">게시글 제목</p>
                    <p class="board-details">카테고리 | 지역 | 1/10</p>
                </div>
            </div>
            <div class="board-item">
                <div class="board-img"></div>
                <div class="board-info">
                    <h3>게시글 제목</h3>
                    <p class="board-name">게시글 제목</p>
                    <p class="board-details">카테고리 | 지역 | 1/10</p>
                </div>
            </div>
        </section>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>