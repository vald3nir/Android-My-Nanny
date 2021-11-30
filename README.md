# App My Nanny
A mobile app of an electronic babysitter for the IoT Discipline.

Master in Computer Science - UFC

## Overview

![overview](https://user-images.githubusercontent.com/23506996/144066611-343f705d-c392-4fe8-98a0-f574b05f7271.png)

### Application

![splash](https://user-images.githubusercontent.com/23506996/144067086-9150c9fc-793a-47ed-929d-31b479e42f0f.png)
![registro](https://user-images.githubusercontent.com/23506996/144067084-13e62c77-cdab-4edc-82fb-761c2ac4b2e4.png)
![login](https://user-images.githubusercontent.com/23506996/144067088-13f0fc96-a048-4037-a9c9-af432ac33c50.png)
![monitor](https://user-images.githubusercontent.com/23506996/144067078-056a527a-24d6-449d-8221-efa6aaea7ec7.png)

## Video server configuration on Raspberry Pi [Linux]

### Install Motion 

    sudo apt-get install motion
  
### Config Motion

    sudo nano /etc/motion/motion.conf
        
- Make sure 'daemon' is ON.
- Set 'framerate' anywhere in between 1000 to 1500.
- Keep 'Stream_port' to 8081.
- 'Stream_quality' should be 100.
- Change 'Stream_localhost' to OFF.
- Change 'webcontrol_localhost' to OFF.
- Set 'quality' to 100.
- Set 'width' & 'height' to 640 & 480.
- Set 'post_capture' to 5.
- Press ctrl + x to exit.

### Setup boot

    sudo nano /etc/default/motion

- Set ' start_motion_daemon ' to yes

### Run Motion

    sudo service motion restart
    sudo motion
    
### To show

    http://192.168.0.40:8081/

       
      

