###
Perspective. 

Move the mouse left and right to change the field of view (fov).
Click to modify the aspect ratio. The perspective() function
sets a perspective projection applying foreshortening, making 
distant objects appear smaller than closer ones. The parameters 
define a viewing volume with the shape of truncated pyramid. 
Objects near to the front of the volume appear their actual size, 
while farther objects appear smaller. This projection simulates 
the perspective of the world more accurately than orthographic projection. 
The version of perspective without parameters sets the default 
perspective and the version with four parameters allows the programmer 
to set the area precisely.
###

setup: ->
    @size 640, 360, @P3D
    @noStroke()

draw: ->
    @lights()
    @background 204
    cameraY = @height/2
    fov = @mouseX / @width * @PI/2
    cameraZ = cameraY / @tan( fov / 2 )
    aspect = @width / @height
    
    (aspect = aspect/2) if @mousePressed
    
    @perspective fov, aspect, cameraZ/10, cameraZ*10
    
    @translate @width/2+30, @height/2, 0
    @rotateX -@PI/6
    @rotateY @PI/2 + @mouseY/@height * @PI
    @box 45
    @translate 0, 0, -50
    @box 30
    
