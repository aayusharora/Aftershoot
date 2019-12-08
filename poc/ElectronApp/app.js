
let max = 5;
let min = 1;
let upload = document.getElementById('upload');
let category = document.getElementById('category');
let goodClick = document.getElementById('goodClick');
let badClick = document.getElementById('badClick');
let faceClick = document.getElementById('faceClick');
let good = document.getElementById('good');
let bad = document.getElementById('bad');
let faces = document.getElementById('object');


goodClick.onclick = function(){
    bad.style.display = 'none';
    faces.style.display = 'none';
    good.style.display = 'flex';
}

badClick.onclick = function(){
    good.style.display = 'none';
    faces.style.display = 'none';
    bad.style.display = 'flex';
}

faceClick.onclick = function(){
    bad.style.display = 'none';
    good.style.display = 'none';
    faces.style.display = 'flex';
}

function renderImage(e) {
  let array = Array.from(e.target.files);
   for (let file of array) {
    let i = array.indexOf(file);
    checkBlur(e.target.files[i].path, function(data){
      data = JSON.parse(data);
      if(data.res == 'blur') addInBad(e.target.files[i].path, data.per)
      if(data.res == 'unblur') addInGood(e.target.files[i].path, data.per)
      else if(data.res == 'bokeh') addInObject(e.target.files[i].path, data.per)
    });
  };
}


document.getElementById('inputFile').addEventListener('change', function(e){
  renderImage(e);
})

function checkBlur(path, cb) {
  console.log(path)
  var xhttp = new XMLHttpRequest();
  var params = 'path='+path;
  xhttp.open("POST", "http://localhost:5000/detect", true);
  xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        upload.style.display = 'none';
        category.style.display = 'block';
        cb(xhttp.responseText);
      }
  };
 
  xhttp.send(params);
}

function addInGood(path, per) {
 img_create(path, 'goodImage', per);
}

function addInObject(path, per) {
  img_create(path, 'normalImage', per);
 }
 
function addInBad(path, per) {
  img_create(path, 'badImage', per);
 }

function img_create(src, alt, per) {
  //var ar = ['red', 'green', 'orange', 'black']
  var div = document.createElement('div');
  div.className = 'bricklayer-column';
  var secdiv = document.createElement('div');
  secdiv.className = 'box';
  //secdiv.style.border='1px dashed black';
 // var random = Math.floor(Math.random() * (+max - +min)) + +min; 
  //secdiv.style.backgroundColor = ar[random];
 var img = document.createElement('img');
 var p = document.createElement('p');
 p.innerText = per;
 img.src = src;
 img.addEventListener('click', openImage);
  if ( alt != null ) img.alt = alt;
  secdiv.appendChild(img);
  secdiv.appendChild(p);
  div.appendChild(secdiv);
  if(alt == 'goodImage')  document.getElementById('good').appendChild(div);
  if(alt == 'badImage')  document.getElementById('bad').appendChild(div); 
  if(alt == 'normalImage')  document.getElementById('object').appendChild(div); 
}

function openImage(){
  let img = this;
  let style = getComputedStyle(img);
  if(style.transform == 'matrix(5, 0, 0, 5, 0, 0)') {
    img.style.transform = 'none';
  }
  else {
    img.style.transform = 'scale(5)'
  }
}
