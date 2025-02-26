body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #000;
    color: #fff;
}

header {
    background-color: #34495e;
    color: #ececf1;
    padding: 20px;
    text-align: center;
}

header h1 {
    font-size: 2.5rem;
    color: #f39c12;
}

nav ul {
    list-style: none;
    padding: 0;
}

nav ul li {
    display: inline;
    margin-right: 20px;
}

nav ul li a {
    color: #ecf0f1;
    text-decoration: none;
    font-size: 1rem;
}

.featured-news, .latest-news {
    padding: 30px;
    background-color: #2c3e50;
    margin: 20px;
    border-radius: 10px;
}

.articles {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
}

.article {
    background-color: #ecf0f1;
    padding: 20px;
    border-radius: 10px;
    cursor: pointer;
    transition: transform 0.3s ease;
}

.article:hover {
    transform: scale(1.05);
}

.article h3 {
    color: #2c3e50;
}

.article img {
    width: 100%;
    border-radius: 10px;
}

.dropdown {
    position: relative;
    display: inline-block;
    margin: 20px;
}

.dropbtn {
    background-color: #f39c12;
    color: white;
    padding: 10px 15px;
    font-size: 1.5rem;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 200px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.dropdown:hover .dropdown-content {
    display: block;
}

.modal {
    display: none;
    position: fixed;
    z-index: 1000;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.4);
}

.modal-content {
    background-color: #fff3cd;
    margin: 5% auto;
    padding: 20px;
    border-radius: 10px;
    width: 80%;
}

.close {
    float: right;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
}
