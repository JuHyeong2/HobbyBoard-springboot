<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.main-container {
    max-width: 1024px;
    margin: 0 auto;
    padding: 2rem 1rem;
}

.breadcrumb {
    margin-bottom: 1rem;
    color: #868e96;
}

/* gathering detail styles */
.gathering-detail {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 2rem;
    margin-bottom: 2rem;
}

.main-image {
    width: 100%;
    padding-bottom: 100%;
    background-color: #f8f9fa;
    border-radius: 8px;
}

.gathering-info {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.gathering-title {
    font-size: 1.5rem;
    font-weight: bold;
}

.price {
    font-size: 1.75rem;
    font-weight: bold;
}

.purchase-btn {
    padding: 1rem;
    background-color: #0066cc;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 1.125rem;
    cursor: pointer;
    margin-top: auto;
}

/* Seller info styles */
.seller-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem;
    border: 1px solid #e9ecef;
    border-radius: 8px;
    margin-bottom: 2rem;
}

.seller-profile {
    display: flex;
    align-items: center;
    gap: 1rem;
}

.profile-image {
    width: 48px;
    height: 48px;
    border-radius: 50%;
    background-color: #f8f9fa;
}

.temperature {
    color: #0066cc;
    font-weight: bold;
}

/* Items grid styles */
.items-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 1rem;
    margin: 1rem 0 2rem;
}

.item-card {
    aspect-ratio: 1;
    background-color: #f8f9fa;
    border-radius: 8px;
}

/* Footer styles */


.footer {
    background-color: #f0f2ff;
    padding: 3rem 1rem;
    margin-top: 4rem;
}

.app-download {
    text-align: center;
    margin-bottom: 3rem;
}

.store-buttons {
    display: flex;
    justify-content: center;
    gap: 1rem;
    margin-top: 1rem;
}

.store-buttons button {
    padding: 0.75rem 1.5rem;
    border: 1px solid #0066cc;
    border-radius: 4px;
    background-color: white;
    color: #0066cc;
    cursor: pointer;
}

.borad-slider {
    display: flex;
    overflow-x: auto;
    gap: 1rem;
    padding: 1rem 0;
    

    /* height: 500px; */
    width: 100%;
    padding: 1rem 0;
    justify-content: space-between;
    flex-direction: row;
    align-items: center; 
}

.borad-card:hover {
    transform: translateY(-10px);
    transition-duration: 0.3s;
}

.borad-card {
    min-width: 250px;
    padding: 1rem;
    border: 1px solid #e5e5e5;
    border-radius: 8px;
    transition-duration: 0.3s;
    box-shadow: 5px 5px 20px #f3f3f3;
    
}

img {
	width: 100%;
	height: 100px;
	
}

</style>
</head>
<body>
	<jsp:include page="../common/top.jsp"/>
	
    <main class="main-container">
        <div class="breadcrumb">
            <span>홈</span> &gt; <span>코딩</span> &gt; <span></span>
        </div>

        <div class="gathering-detail">
            <div class="gathering-images">
                <div class="main-image"></div>
                <div class="image-thumbnails"></div>
            </div>

            <div class="gathering-info">
                <h1 class="gathering-title">코드폭격기 신나는 코딩모임</h1>
                <div class="price">코딩모임</div>
                <div class="gathering-meta">
                    <span class="upload-time">종로구/4시간 전</span>
                </div>
                <div class="gathering-description">
                    <p>세미프로젝트를 만들자</p>
                    <p>코드폭격 가보자잇</p>
                </div>
                <button class="purchase-btn">참가하기</button>
            </div>
        </div>

        <section class="seller-info">
            <div class="seller-profile">
                <div class="profile-image"><img alt="board-img" src="#"></div>
                <div class="seller-details">
                    <h3>케로로중사</h3>
                    <p>이야~ 코드폭격기 ㅋㅋㅋㅋ</p>
                </div>
            </div>
            <div class="temperature">
                <span></span>
            </div>
        </section>
        <section class="seller-info">
            <div class="seller-profile">
                <div class="profile-image"><img alt="board-img" src="#"></div>
                <div class="seller-details">
                    <h3>타마마 이병</h3>
                    <p>코드좀 침??ㅋㅋ</p>
                </div>
            </div>
            <div class="temperature">
                <span></span>
            </div>
        </section>
        <section class="seller-info">
            <div class="seller-profile">
                <div class="profile-image"><img alt="board-img" src="#"></div>
                <div class="seller-details">
                    <h3>뚜뇽</h3>
                    <p>응 나 아기천사</p>
                </div>
            </div>
            <div class="temperature">
                <span></span>
            </div>
        </section>


        <section class="seller-items">
            <h2>코딩 관련 모임</h2>
            <div class="items-grid">
                <!-- 판매 물품 그리드 아이템들 -->
                <div class="board-card">
                    <div class="board-img" ><img alt="board-img" src="image/park.jpg"></div>
                    <h3>게시글 제목</h3>
                    <p>25.01.01</p>
                </div>
                <div class="board-card">
                    <div class="board-img" ><img alt="board-img" src="image/park.jpg"></div>
                    <h3>게시글 제목</h3>
                    <p>25.01.01</p>
                </div>
                <div class="board-card">
                    <div class="board-img" ><img alt="board-img" src="image/park.jpg"></div>
                    <h3>게시글 제목</h3>
                    <p>25.01.01</p>
                </div>
                <div class="board-card">
                    <div class="board-img" ><img alt="board-img" src="image/park.jpg"></div>
                    <h3>게시글 제목</h3>
                    <p>25.01.01</p>
                </div>
            </div>
        </section>

        <!-- <section class="related-items">
            <h2>인기 게시글</h2>
            <div class="items-grid">
                인기 매물 그리드 아이템들
                                <div class="board-card">
                    <div class="board-img" ><img alt="board-img" src="image/park.jpg"></div>
                    <h3>게시글 제목</h3>
                    <p>25.01.01</p>
                </div>
                <div class="board-card">
                    <div class="board-img" ><img alt="board-img" src="image/park.jpg"></div>
                    <h3>게시글 제목</h3>
                    <p>25.01.01</p>
                </div>
                <div class="board-card">
                    <div class="board-img" ><img alt="board-img" src="image/park.jpg"></div>
                    <h3>게시글 제목</h3>
                    <p>25.01.01</p>
                </div>
                <div class="board-card">
                    <div class="board-img" ><img alt="board-img" src="image/park.jpg"></div>
                    <h3>게시글 제목</h3>
                    <p>25.01.01</p>
                </div>
            </div>
        </section> -->
    </main>
	
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>