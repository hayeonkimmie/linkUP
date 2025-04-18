const tabs = document.querySelectorAll('.tab');
const contents = document.querySelectorAll('.tab-content');

tabs.forEach(tab => {
  tab.addEventListener('click', () => {
    const target = tab.dataset.tab;

    tabs.forEach(t => t.classList.remove('active'));
    tab.classList.add('active');

    contents.forEach(content => {
      content.classList.remove('active');
    });

    document.getElementById(target).classList.add('active');
  });
});

const toggleBtn = document.querySelector('.dropdown-toggle');
const dropdown = document.querySelector('.dropdown-menu');

toggleBtn.addEventListener('click', () => {
  dropdown.classList.toggle('show');
});

window.addEventListener('click', (e) => {
  if (!e.target.closest('.filters')) {
    dropdown.classList.remove('show');
  }
});
