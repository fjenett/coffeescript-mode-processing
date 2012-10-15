###
Request Image
by Ira Greenberg ( From Processing for Flash Developers). 

Shows how to use the requestImage() function with preloader animation. 
The requestImage() function loads images on a separate thread so that 
the sketch does not freeze while they load. It's very useful when you are
loading large images. 

These images are small for a quick download, but try it with your own huge 
images to get the full effect. 
###

setup: ->
    
    size 640, 360
    smooth()
    
    @imgCount = 12
    @imgs = new Array( @imgCount )

    # Keeps track of loaded images (true or false)
    @loadStates = new Array( @imgCount )
    
    @imgW = width/@imgCount

    # Load images asynchronously
    for i in [0...@imgCount]
        @imgs[i] = requestImage "PT_anim" + nf(i, 4) + ".gif"

draw: ->
    
    background 0
    
    # Start loading animation
    @runLoaderAni()
    
    for i in [0...@imgs.length]
        
        # Check if individual images are fully loaded
        if @imgs[i].loaded
            
            # As images are loaded set true in boolean array
            @loadStates[i] = yes
    
    # When all images are loaded draw them to the screen
    @drawImages() if @checkLoadStates()


drawImages: ->
    
    y = (height - @imgs[0].height) / 2
    
    for i in [0...@imgs.length]
        
        image @imgs[i], width/@imgs.length*i, y, @imgs[i].height, @imgs[i].height


# Loading animation
runLoaderAni: ->
    
    # Only run when images are loading
    if !@checkLoadStates()
        
        ellipse( @loaderX, @loaderY, 10, 10)
        @loaderX += 2
        @loaderY = height/2 + sin(@theta) * (height/8)
        @theta += PI/22
        
        # Reposition ellipse if it goes off the screen
        @loaderX = -5 if (@loaderX > width + 5)
    

# Return true when all images are loaded - no false values left in array 
checkLoadStates: ->
    
    for i in [0...@imgs.length]
        return false unless @loadStates[i]
    
    return true




