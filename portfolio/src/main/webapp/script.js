// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

function popfunc(){
    shown.pop()
}
/**
 * gets a random celebration picture.
 */
var shown = []
function getRandomCelebration() {
    var imageIndex = Math.floor(Math.random() * 10) + 1;
    if (shown.length < 11){ 
        if (shown.includes(imageIndex)){
            while( shown.includes(imageIndex)){
                imageIndex = Math.floor(Math.random() * 10) + 1
            }
        }
    }else{
        shown.forEach(popfunc)
    }
    shown.push(imageIndex)
    const imgUrl = 'images/f-' + imageIndex + '.jpg';

    const imgElement = document.createElement('img');
    imgElement.src = imgUrl;

    const imageContainer = document.getElementById('random-image-container');
    // Remove the previous image.
    imageContainer.innerHTML = '';
    imageContainer.appendChild(imgElement);
}

function getRandomComment() {
  // The fetch() function returns a Promise because the request is asynchronous.
  console.log("button pressed");
  const responsePromise = fetch('/data');
  // When the request is complete, pass the response into handleResponse().
  responsePromise.then(handleResponse);
}

function handleResponse(response) {
  // response.text() returns a Promise, because the response is a stream of
  // content and not a simple variable.
  const textPromise = response.text();

  // When the response is converted to text, pass the result into the
  // addQuoteToDom() function.
  textPromise.then(addCommentToDom);
}

function addCommentToDom(comment) {
  console.log('Adding quote to dom: ' + comment);

  const quoteContainer = document.getElementById('comment');
  quoteContainer.innerText = comment;
  
}