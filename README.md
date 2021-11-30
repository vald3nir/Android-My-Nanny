# App My Nanny
Mobile app of an electronic nanny


## Overview

![overview](https://user-images.githubusercontent.com/23506996/144062063-baf98cac-7531-4581-9139-09d6e38f129f.png)


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

       
      

