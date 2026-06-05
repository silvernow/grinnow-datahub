<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setStatus(404);
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta name="robots" content="noindex, nofollow" />
		<title>ERROR - 알수없는오류</title>
		<style>
	  		:root {
	    		--primary: #198754; /* Bootstrap의 success 색상 */
	    		--gray: #6c757d;
	    		--light-gray: #f8f9fa;
	    		--dark: #212529;
	  		}
	  		body {
			    margin: 0;
			    font-family: 'Noto Sans KR', system-ui, sans-serif;
			    background: var(--light-gray);
			    color: var(--dark);
			    display: flex;
			    flex-direction: column;
			    align-items: center;
			    justify-content: center;
			    height: 100vh;
			    text-align: center;
			    padding: 1rem;
			}
			.container {
			    background: white;
			    border-radius: 0.5rem;
			    box-shadow: 0 4px 20px rgba(0,0,0,0.1);
			    max-width: 480px;
			    width: 100%;
			    padding: 2rem;
			}
			h1 {
			    font-size: 4rem;
			    color: var(--primary);
			    margin: 0 0 0.5rem;
			    font-weight: 700;
			}
			h2 {
				font-size: 1.4rem;
			    margin-bottom: 1rem;
			}
		  	p {
			    font-size: 0.95rem;
		    	color: var(--gray);
		    	margin-bottom: 1.5rem;
		  	}
		  	.btn {
		    	display: inline-block;
		    	padding: 0.6rem 1.2rem;
		    	border-radius: 0.375rem;
		    	text-decoration: none;
		    	font-weight: 500;
		    	transition: all 0.2s ease;
		  	}
		  	.btn-primary {
			    background-color: var(--primary);
		    	color: #fff;
		    	border: 1px solid var(--primary);
		  	}
		  	.btn-primary:hover {
			    background-color: #157347;
		  	}
		  	.btn-outline {
			    background: transparent;
		    	border: 1px solid var(--gray);
		    	color: var(--gray);
		  	}
		  	.btn-outline:hover {
			    border-color: var(--primary);
		    	color: var(--primary);
		  	}
		  	.search-box {
			    display: flex;
		    	gap: 0.5rem;
		    	margin-bottom: 1rem;
		  	}
		  	.search-box input {
			    flex: 1;
		    	padding: 0.6rem;
		    	border: 1px solid #ced4da;
		    	border-radius: 0.375rem;
		  	}
		  	@media (max-width: 480px) {
			    h1 { font-size: 3rem; }
		    	h2 { font-size: 1.2rem; }
			}
		</style>
	</head>
	<body>
  		<div class="container">
	    	<h1>ERROR</h1>
	    	<h2>알수없는오류.</h2>
    		<p>알수없는오류가 발생하였습니다.</p>
    		<div style="display:flex; justify-content:center; gap:0.5rem;">
      			<a href="<c:url value='/'/>" class="btn btn-outline">홈으로</a>
      			<a href="mailto:support@grintrader.co.kr" class="btn btn-outline">문의하기</a>
    		</div>
  		</div>
	</body>
</html>
