document.querySelectorAll('.article').forEach(article => {
    article.addEventListener('click', function() {
        const title = this.getAttribute('data-title');
        const content = this.getAttribute('data-content');
        document.getElementById('modal-title').textContent = title;
        document.getElementById('modal-content').textContent = content;
        document.getElementById('modal').style.display = "block";
    });
});

document.querySelector('.close').addEventListener('click', function() {
    document.getElementById('modal').style.display = "none";
});

function showCategoryNews(category) {
    let categoryNews = '';

    if (category === 'Technology') {
        categoryNews = `
            <div class="article">
                <h3>AI in 2025: The Future of Artificial Intelligence</h3>
                <p>Discover how AI is expected to revolutionize various industries.</p>
            </div>`;
    } else if (category === 'Health') {
        categoryNews = `
            <div class="article">
                <h3>Telemedicine: The Future of Healthcare</h3>
                <p>Telemedicine is providing easier access to healthcare worldwide.</p>
            </div>`;
    }

    document.getElementById('category-content').innerHTML = categoryNews;
}
