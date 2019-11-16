# image_processing_mapreduce
Evaluating some image processing algorithms using the cloud both AWS and GCP
# VGGFACE2 Dataset Enhancement

Over the last two decades, face recognition and face verification have been interesting areas of research in computer vision. Due to the scope, properties and difficulties of these problems, different types of researchers are involved in them such computer science researchers, psychologists and neuroscientists.

The general problem definition of Face recognition problems from computer vision prospective is to detect, identify and verify one or more persons in the image using a dataset of faces.

Face detection is the process of reliably finding faces and their landmarks in an image. It is the core process in face recognition and face verification systems.

 A massive amount of research and advancement have been conducted in this area leveraging the advancement in computation power. Over the last few years, so many high quality datasets have been proposed, e.g.. LFW, VGGFACE2…etc. most of these datasets are the bases used for developing and architecting some of the state-of-the-art face detecting algorithms such as MTCNN. 

Each dataset has different number of images, for example, LFW has 13233 images for faces captured in the wild. Also, VGGFACE2 has more than 3 million images which various based on Pose and age…. It is notably that each image may have one or more faces. The majority have just one face. In order to achieve higher accuracy in face detection systems. 

My contribution would be on enhancing the accuracy on the images which has one face alone and then working on images with 2 faces then 3 faces in order to enhance a general face detection system reliable and real time enough that could be used to improve face recognition and face verification systems.


### Modeling MapReduce Programming

- we upload the VGGFACE2 to the AWS.
- we put the dataset into the HDFS such that each file name is its file name appended to its identity name as per the dataset description.
- we create interface for processing the images and methods of detecting faces, resizing images…etc.
- we implement the interface with based on the required processing ( in this case , face detection and image enhancement; resizing) face detection using OpenCV , and image resize to 299 by 299 for later use to train Inception V4 model. we will use the built-in face detection algorithms preferably the LBP classifier for better accuracy. Another Library, the DLIB library, could be used for better face detection accuracy, specifically, when working on 5 landmark face detection.
 
In the Mapper part:
- integrate and load OpenCV library.
- load the required interface implementation as mentioned above.
- we detect number of faces per image.
- the mapper emits the number of faces as well as the resized image.
 
In the Reducer part:
- process the number of faces per image.
- assign each image to its corresponding dataset based on the number of faces.
 
 
An ETL integration philosophy that could be applied:
A cron job for watching directory change could run periodically uploading the new images to the HDFS for processing the new images and update the dataset as described above. This would be beneficial in the cases such as scrapping the web for new images with difference faces or merging many datasets together.
The module will look for images in a specific directory, then upload the new images at specific time to the HDFS for processing. Once they uploaded , they will be deleted from that folder.


### Todos

 - Updaod dataset to AWS S3
 - Updaoad project to AWS
 - Updaoad project to GCP

