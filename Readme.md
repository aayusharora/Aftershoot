<p align='center'>
  <img src='images/Screenshot 2019-12-03 at 8.24.22 PM.png'>
</p>

## Why Aftershoot?
My uncle is a wedding photographer. Getting 200 best photos for the album out of 4000 photos after each wedding is 1-2 days frustrating process for him. With technology, filtering the same amount technically bad photos is feasible in lesser than 5 minutes. Like my uncle, there are 7 Billion photographers on the planet who can cut down 99% of their time with Aftershoot.

## How do we envision this?
Solving the problem for a professional photographer is just a step towards our vision. Our final vision is to give this app to every person on this planet to gather his best shots filtering out all blurry, out of focus, overexposed, underexposed and duplicates.

## How is the progress so far?
We have spent a great amount of time last quarter to analyse the extent of the problem. In the last month, we have successfully developed a Proof of Concept to classify blurry, bokeh and unblurry images. We have extensively use Cloud AutoMl to achieve this. 

Please have a look at our POC.
https://drive.google.com/file/d/1tt2JZPNd1QyShZr9fxt7YF9SxoIxByCO/view

And also some AutoML exciting results :D

| Feature       | Status        |Accuracy|
| ------------- |:-------------:| -----:|
| Blur          | Done          | 81%   |
| Exposure      | Done          | 82%   |
| Blinks        | In Progress   |       |
| Duplicates    | In Progress   |       |
| ...           | In Progress   |       |

### AutoML Results:
<h4>Blur Detection Results:</h4>
<p>Bokeh</p>
<img src='./images/1.png' style='max-width: 200px' alt='bokeh'/>
<p>Blurry</p>
<img src='./images/2.png' style='max-width: 200px' alt='blurry'/>
<h4>Exposure Detection</h4>
<p>UnderExposed</p>
<img src='./images/underexposed.png' style='max-width: 200px' alt='underexposed'/>


<p>#AndroidDevChallenge</p>
<a href='https://github.com/aayusharora/Aftershoot/blob/master/cover-letter.md'>Cover Letter</a>
