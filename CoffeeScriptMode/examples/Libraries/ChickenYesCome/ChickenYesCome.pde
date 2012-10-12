###
Permutation example of Combinatorics library

Get library here:
https://github.com/fjenett/combinatorics
###

#import de.bezier.math.combinatorics.*

setup: ->

    @size 400, 200
    
    @perm = new Permutation 3
    @words = ['Chicken', 'Yes', 'Come']
    
    @textFont @createFont 'Helvetica', 62
    
    @next = yes
    @noLoop()

draw: ->
    
    if @next
        @background 255
        @fill 0
        
        p = @perm.next()
        yy = 56
        
        for n in p
            @text @words[n], 16, yy
            yy += 64
            
        @next = no

mouseReleased: ->
    
    @next = yes
    @redraw()

keyPressed: ->
    
    @perm.rewind() unless @perm.hasMore()
    @next = yes
    @redraw()
