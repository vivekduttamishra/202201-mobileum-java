# Lecture Notes

Please follow the instruction to prepare yourself for the course.




## 1. Create Folder Structure

```cmd
c:\>md current-training-name
c:\>md current-training-name\trainers-work
c:\>md current-training-name\my-work
```

#### Folder Structure should be something like this

<pre>
[c drive]
    [current-training-name]
        [trainers-work]
            
        [my-work]
        
</pre>


## 2. Clone Trainer's Repository to trainers-work folder

```cmd
c:\react-native-traing\trainers-work> git clone https://github.com/vivekduttamishra/trainer-repo-name .
```

### Notes

1. Note the command must be executed in trainers-work folder 
2. Note the trailing dot (.) after the url and a blank space
3. If git command fails, download and install git client from 

#### https://gitforwindows.org/
#### https://git-scm.com/downloads 

## 3. Copy the following files from trainers-work to my-work folder

```cmd
c:\current-training-name\trainers-work> copy git-*.* ..\my-work

c:\current-training-name\trainers-work> cd ..\my-work
c:\current-training-name\my-work> git-ignore
c:\current-training-name\my-work> git-ignore-nodejs

```



## 4. Create Your own Github repository for the training 

#### Note: 

1. You may need to create an account on github.com
2. Assuming that your url is  https://github.com/participant01/react-native.git



## 5. Initalize your repository in my-work folder 


```cmd
c:\current-training-name\my-work>git-init your-github-repo-url

```
## 6. Share the link of your repository in **Partcipant Information Form** that would be shared during the training.


# Part 2

## To download trainer-work whenever required

```cmd
c:\current-training-name\trainers-work>git pull
```

### Note: 
* Never change anything in this folder. 
* If you need you can copy the content from this folder to your folder 

## To upload your assignments

* Create your own examples in **my-work** folder
* to upload your content 

```cmd
c:\current-training-name\my-work>git-update remark-for-this-update
```



