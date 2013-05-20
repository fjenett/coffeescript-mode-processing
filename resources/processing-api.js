
/*
 +    Automatically added code to allow for Processing API to "just work"
 +    ... as it removes the need for "@" signs everywhere. A call to 
 +    injectProcessingApi(this) is being added to top of your setup() method.
 +
 +    This file is being minified using Google closure:
 +    $ closure --js processing-api.js --js_output_file processing-api-min.js
 + 
 +    It is later added to the head of the generated JavaScript file 
 +    "SketchName_compiled.js" and a iffy is added to setup to inject it into the 
 +    sketch instance. Quite complicated, oh boy!
 +
 L + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + */

var ADD = ALIGN_CENTER = ALIGN_LEFT = ALIGN_RIGHT = ALPHA = ALPHA_MASK = ALT = AMBIENT = ARGB = ARROW = 
    BACKSPACE = BASELINE = BEVEL = BLEND = BLUE_MASK = BLUR = BOTTOM = BURN = 
    CENTER = CHATTER = CLOSE = CMYK = CODED = COMPLAINT = COMPONENT = COMPOSITE = CONCAVE_POLYGON = CONTROL = 
        CONVEX_POLYGON = CORNER = CORNERS = CROSS = CUSTOM = 
    DARKEST = DEGREES = DEG_TO_RAD = DELETE = DIAMETER = DIFFERENCE = DIFFUSE = DILATE = DIRECTIONAL = 
        DISABLED = DODGE = DOWN = DXF = 
    ENTER = EPSILON = ERODE = ESC = EXCLUSION = 
    GIF = GRAY = GREEN_MASK = GROUP = 
    HALF = HALF_PI = HAND = HARD_LIGHT = HINT_COUNT = HSB = 
    IMAGE = INVERT = 
    JAVA2D = JPEG = 
    LEFT = LIGHTEST = LINES = LINUX = 
    MACOSX = MAX_FLOAT = MAX_INT = MITER = MODEL = MOVE = MULTIPLY = 
    NORMAL = NORMALIZED = NO_DEPTH_TEST = NTSC = 
    ONE = OPAQUE = OPEN = OPENGL = ORTHOGRAPHIC = OVERLAY = 
    P2D = P3D = PAL = PDF = PERSPECTIVE = PI = PIXEL_CENTER = POINT = POINTS = POSTERIZE = PROBLEM = PROJECT = 
    QUADS = QUAD_STRIP = QUARTER_PI = 
    RADIANS = RADIUS = RAD_TO_DEG = RED_MASK = REPLACE = RETURN = RGB = RIGHT = ROUND = 
    SCREEN = SECAM = SHIFT = SOFT_LIGHT = SPECULAR = SQUARE = SUBTRACT = SVIDEO = 
    TAB = TARGA = TEXT = TFF = THIRD_PI = THRESHOLD = TIFF = TOP = TRIANGLES = TRIANGLE_FAN = TRIANGLE_STRIP = 
        TUNER = TWO = TWO_PI = 
    UP = 
    WAIT = WHITESPACE = 
    XML = 
    /* + + + + + + + + + + + + + + classes + + + + + + + + + + + + + */
    ArrayList = 
    BufferedReader = 
    Character = 
    HashMap = 
    Integer = 
    PFont = PGraphics = PImage = PShader = PShape = PVector = PrintWriter = 
    StringBuffer = 
    /* + + + + + + + + + + + + + + functions + + + + + + + + + + + + + */
    abs = acos = addChild = alpha = ambient = ambientLight = append = applyMatrix = arc = asin = atan = atan2 = 
    background = beginCamera = beginContour = beginRaw = beginRecord = beginShape = bezier = bezierDetail = 
        bezierPoint = bezierTangent = bezierVertex = binary = bind = blend = blendColor = blendMode = blue = 
        box = breakShape = brightness = 
    cache = camera = ceil = clip = color = colorMode = concat = constrain = copy = cos = createFont = 
        createGraphics = createImage = createInput = createOutput = createPath = createReader = createShape = 
        createWriter = cursor = curve = curveDetail = curvePoint = curveTangent = curveTightness = curveVertex = 
    day = degrees = directionalLight = dist = draw = 
    ellipse = ellipseMode = emissive = end = endCamera = endContour = endRaw = endRecord = endShape = 
        exit = exp = expand = 
    fill = filter = floor = focused = frustum = 
    get = green = 
    hex = hint = hour = hue = 
    image = imageMode = 
    join = 
    keyPressed = keyReleased = keyTyped = 
    lerp = lerpColor = lightFalloff = lightSpecular = lights = line = loadBytes = loadFont = loadImage = 
        loadMatrix = loadPixels = loadShader = loadShape = loadStrings = loadType = log = loop = 
    mag = map = match = matchAll = max = millis = min = minute = modelX = modelY = modelZ = month = mouseButton = 
        mouseClicked = mouseDragged = mouseMoved = mousePressed = mouseReleased = 
    nf = nfc = nfp = nfs = noClip = noCursor = noFill = noHint = noLights = noLoop = noSmooth = noStroke = 
        noTint = noise = noiseDetail = noiseSeed = norm = normal = 
    open = openStream = ortho = 
    parseByte = perspective = point = pointLight = popMatrix = popStyle = pow = print = printCamera = 
        printMatrix = printProjection = println = pushMatrix = pushStyle = 
    quad = quadraticVertex = 
    radians = random = randomSeed = rect = rectMode = red = redraw = requestImage = resetMatrix = resetShader = 
        reverse = rotate = rotateX = rotateY = rotateZ = round = 
    saturation = save = saveBytes = saveFile = saveFrame = savePath = saveStream = saveStrings = saveType = 
        scale = screenX = screenY = screenZ = second = selectFolder = selectInput = selectOutput = set = setup = 
        shader = shape = shapeMode = shearX = shearY = shininess = shorten = sin = /* size = */ 
        sketchFile = sketchPath = smooth = sort = specular = sphere = sphereDetail = splice = split = splitTokens = 
        spotLight = sq = sqrt = start = stop = stroke = strokeCap = strokeJoin = strokeWeight = subset = 
    tan = text = textAlign = textAscent = textDescent = textFont = textLeading = textMode = textSize = textWidth = 
        texture = textureMode = tint = translate = triangle = trim = 
    unbinary = unhex = updatePixels = 
    vertex = 
    year = 
    /* + + + + + + + + + + + + + + + + getters + + + + + + + + + + + + */
    // displayHeight = displayWidth = 
    // frameCount = frameRate = 
    // height = 
    // key = keyCode = keyPressed = 
    // mousePressed = mouseX = mouseY = 
    // online = 
    // pixels = pmouseX = pmouseY = 
    // screenHeight = screenWidth = 
    // width = 
    null;

//var injectCalledTimes = 0;
var injectProcessingApi = function(processing) {
    // injectCalledTimes++;
    // console.log({
    //     called: injectCalledTimes,
    //     context: this,
    //     defineGetter: __defineGetter__,
    //     //receiver_class: klass,
    //     processing: processing,
    //     processing_typeof: typeof processing,
    //     processing_proto: processing.prototype,
    //     processing_constructor: processing.constructor
    // });
    /* + + + + + + + + + + + + +
     + constants
     + + + + + + + + + + + + + */
    ADD = processing.ADD;
    ALIGN_CENTER = processing.ALIGN_CENTER;
    ALIGN_LEFT = processing.ALIGN_LEFT;
    ALIGN_RIGHT = processing.ALIGN_RIGHT;
    ALPHA = processing.ALPHA;
    ALPHA_MASK = processing.ALPHA_MASK;
    ALT = processing.ALT;
    AMBIENT = processing.AMBIENT;
    ARGB = processing.ARGB;
    ARROW = processing.ARROW;
    BACKSPACE = processing.BACKSPACE;
    BASELINE = processing.BASELINE;
    BEVEL = processing.BEVEL;
    BLEND = processing.BLEND;
    BLUE_MASK = processing.BLUE_MASK;
    BLUR = processing.BLUR;
    BOTTOM = processing.BOTTOM;
    BURN = processing.BURN;
    CENTER = processing.CENTER;
    CHATTER = processing.CHATTER;
    CLOSE = processing.CLOSE;
    CMYK = processing.CMYK;
    CODED = processing.CODED;
    COMPLAINT = processing.COMPLAINT;
    COMPONENT = processing.COMPONENT;
    COMPOSITE = processing.COMPOSITE;
    CONCAVE_POLYGON = processing.CONCAVE_POLYGON;
    CONTROL = processing.CONTROL;
    CONVEX_POLYGON = processing.CONVEX_POLYGON;
    CORNER = processing.CORNER;
    CORNERS = processing.CORNERS;
    CROSS = processing.CROSS;
    CUSTOM = processing.CUSTOM;
    DARKEST = processing.DARKEST;
    DEGREES = processing.DEGREES;
    DEG_TO_RAD = processing.DEG_TO_RAD;
    DELETE = processing.DELETE;
    DIAMETER = processing.DIAMETER;
    DIFFERENCE = processing.DIFFERENCE;
    DIFFUSE = processing.DIFFUSE;
    DILATE = processing.DILATE;
    DIRECTIONAL = processing.DIRECTIONAL;
    DISABLED = processing.DISABLED;
    DODGE = processing.DODGE;
    DOWN = processing.DOWN;
    DXF = processing.DXF;
    ENTER = processing.ENTER;
    EPSILON = processing.EPSILON;
    ERODE = processing.ERODE;
    ESC = processing.ESC;
    EXCLUSION = processing.EXCLUSION;
    GIF = processing.GIF;
    GRAY = processing.GRAY;
    GREEN_MASK = processing.GREEN_MASK;
    GROUP = processing.GROUP;
    HALF = processing.HALF;
    HALF_PI = processing.HALF_PI;
    HAND = processing.HAND;
    HARD_LIGHT = processing.HARD_LIGHT;
    HINT_COUNT = processing.HINT_COUNT;
    HSB = processing.HSB;
    IMAGE = processing.IMAGE;
    INVERT = processing.INVERT;
    JAVA2D = processing.JAVA2D;
    JPEG = processing.JPEG;
    LEFT = processing.LEFT;
    LIGHTEST = processing.LIGHTEST;
    LINES = processing.LINES;
    LINUX = processing.LINUX;
    MACOSX = processing.MACOSX;
    MAX_FLOAT = processing.MAX_FLOAT;
    MAX_INT = processing.MAX_INT;
    MITER = processing.MITER;
    MODEL = processing.MODEL;
    MOVE = processing.MOVE;
    MULTIPLY = processing.MULTIPLY;
    NORMAL = processing.NORMAL;
    NORMALIZED = processing.NORMALIZED;
    NO_DEPTH_TEST = processing.NO_DEPTH_TEST;
    NTSC = processing.NTSC;
    ONE = processing.ONE;
    OPAQUE = processing.OPAQUE;
    OPEN = processing.OPEN;
    OPENGL = processing.OPENGL;
    ORTHOGRAPHIC = processing.ORTHOGRAPHIC;
    OVERLAY = processing.OVERLAY;
    P2D = processing.P2D;
    P3D = processing.P3D;
    PAL = processing.PAL;
    PDF = processing.PDF;
    PERSPECTIVE = processing.PERSPECTIVE;
    PI = processing.PI;
    PIXEL_CENTER = processing.PIXEL_CENTER;
    POINT = processing.POINT;
    POINTS = processing.POINTS;
    POSTERIZE = processing.POSTERIZE;
    PROBLEM = processing.PROBLEM;
    PROJECT = processing.PROJECT;
    QUADS = processing.QUADS;
    QUAD_STRIP = processing.QUAD_STRIP;
    QUARTER_PI = processing.QUARTER_PI;
    RADIANS = processing.RADIANS;
    RADIUS = processing.RADIUS;
    RAD_TO_DEG = processing.RAD_TO_DEG;
    RED_MASK = processing.RED_MASK;
    REPLACE = processing.REPLACE;
    RETURN = processing.RETURN;
    RGB = processing.RGB;
    RIGHT = processing.RIGHT;
    ROUND = processing.ROUND;
    SCREEN = processing.SCREEN;
    SECAM = processing.SECAM;
    SHIFT = processing.SHIFT;
    SOFT_LIGHT = processing.SOFT_LIGHT;
    SPECULAR = processing.SPECULAR;
    SQUARE = processing.SQUARE;
    SUBTRACT = processing.SUBTRACT;
    SVIDEO = processing.SVIDEO;
    TAB = processing.TAB;
    TARGA = processing.TARGA;
    TEXT = processing.TEXT;
    TFF = processing.TFF;
    THIRD_PI = processing.THIRD_PI;
    THRESHOLD = processing.THRESHOLD;
    TIFF = processing.TIFF;
    TOP = processing.TOP;
    TRIANGLES = processing.TRIANGLES;
    TRIANGLE_FAN = processing.TRIANGLE_FAN;
    TRIANGLE_STRIP = processing.TRIANGLE_STRIP;
    TUNER = processing.TUNER;
    TWO = processing.TWO;
    TWO_PI = processing.TWO_PI;
    UP = processing.UP;
    WAIT = processing.WAIT;
    WHITESPACE = processing.WHITESPACE;
    XML = processing.XML;
    /* + + + + + + + + + + + + +
     + classes
     + + + + + + + + + + + + + */
    ArrayList = processing.ArrayList;
    BufferedReader = processing.BufferedReader;
    Character = processing.Character;
    HashMap = processing.HashMap;
    Integer = processing.Integer;
    PFont = processing.PFont;
    PGraphics = processing.PGraphics;
    PImage = processing.PImage;
    PShader = processing.PShader;
    PShape = processing.PShape;
    PVector = processing.PVector;
    PrintWriter = processing.PrintWriter;
    StringBuffer = processing.StringBuffer;
    /* + + + + + + + + + + + + +
     + functions
     + + + + + + + + + + + + + */
    abs = processing.abs;
    acos = processing.acos;
    addChild = processing.addChild;
    alpha = processing.alpha;
    ambient = processing.ambient;
    ambientLight = processing.ambientLight;
    append = processing.append;
    applyMatrix = processing.applyMatrix;
    arc = processing.arc;
    asin = processing.asin;
    atan = processing.atan;
    atan2 = processing.atan2;
    background = processing.background;
    beginCamera = processing.beginCamera;
    beginContour = processing.beginContour;
    beginRaw = processing.beginRaw;
    beginRecord = processing.beginRecord;
    beginShape = processing.beginShape;
    bezier = processing.bezier;
    bezierDetail = processing.bezierDetail;
    bezierPoint = processing.bezierPoint;
    bezierTangent = processing.bezierTangent;
    bezierVertex = processing.bezierVertex;
    binary = processing.binary;
    bind = processing.bind;
    blend = processing.blend;
    blendColor = processing.blendColor;
    blendMode = processing.blendMode;
    blue = processing.blue;
    box = processing.box;
    breakShape = processing.breakShape;
    brightness = processing.brightness;
    cache = processing.cache;
    camera = processing.camera;
    ceil = processing.ceil;
    clip = processing.clip;
    color = processing.color;
    colorMode = processing.colorMode;
    concat = processing.concat;
    constrain = processing.constrain;
    copy = processing.copy;
    cos = processing.cos;
    createFont = processing.createFont;
    createGraphics = processing.createGraphics;
    createImage = processing.createImage;
    createInput = processing.createInput;
    createOutput = processing.createOutput;
    createPath = processing.createPath;
    createReader = processing.createReader;
    createShape = processing.createShape;
    createWriter = processing.createWriter;
    cursor = processing.cursor;
    curve = processing.curve;
    curveDetail = processing.curveDetail;
    curvePoint = processing.curvePoint;
    curveTangent = processing.curveTangent;
    curveTightness = processing.curveTightness;
    curveVertex = processing.curveVertex;
    day = processing.day;
    degrees = processing.degrees;
    directionalLight = processing.directionalLight;
    dist = processing.dist;
    draw = processing.draw;
    ellipse = processing.ellipse;
    ellipseMode = processing.ellipseMode;
    emissive = processing.emissive;
    end = processing.end;
    endCamera = processing.endCamera;
    endContour = processing.endContour;
    endRaw = processing.endRaw;
    endRecord = processing.endRecord;
    endShape = processing.endShape;
    exit = processing.exit;
    exp = processing.exp;
    expand = processing.expand;
    fill = processing.fill;
    filter = processing.filter;
    floor = processing.floor;
    focused = processing.focused;
    frustum = processing.frustum;
    get = processing.get;
    green = processing.green;
    hex = processing.hex;
    hint = processing.hint;
    hour = processing.hour;
    hue = processing.hue;
    image = processing.image;
    imageMode = processing.imageMode;
    join = processing.join;
    //keyPressed = processing.keyPressed;
    keyReleased = processing.keyReleased;
    keyTyped = processing.keyTyped;
    lerp = processing.lerp;
    lerpColor = processing.lerpColor;
    lightFalloff = processing.lightFalloff;
    lightSpecular = processing.lightSpecular;
    lights = processing.lights;
    line = processing.line;
    loadBytes = processing.loadBytes;
    loadFont = processing.loadFont;
    loadImage = processing.loadImage;
    loadMatrix = processing.loadMatrix;
    loadPixels = processing.loadPixels;
    loadShader = processing.loadShader;
    loadShape = processing.loadShape;
    loadStrings = processing.loadStrings;
    loadType = processing.loadType;
    log = processing.log;
    loop = processing.loop;
    mag = processing.mag;
    map = processing.map;
    match = processing.match;
    matchAll = processing.matchAll;
    max = processing.max;
    millis = processing.millis;
    min = processing.min;
    minute = processing.minute;
    modelX = processing.modelX;
    modelY = processing.modelY;
    modelZ = processing.modelZ;
    month = processing.month;
    mouseButton = processing.mouseButton;
    mouseClicked = processing.mouseClicked;
    mouseDragged = processing.mouseDragged;
    mouseMoved = processing.mouseMoved;
    //mousePressed = processing.mousePressed;
    mouseReleased = processing.mouseReleased;
    nf = processing.nf;
    nfc = processing.nfc;
    nfp = processing.nfp;
    nfs = processing.nfs;
    noClip = processing.noClip;
    noCursor = processing.noCursor;
    noFill = processing.noFill;
    noHint = processing.noHint;
    noLights = processing.noLights;
    noLoop = processing.noLoop;
    noSmooth = processing.noSmooth;
    noStroke = processing.noStroke;
    noTint = processing.noTint;
    noise = processing.noise;
    noiseDetail = processing.noiseDetail;
    noiseSeed = processing.noiseSeed;
    norm = processing.norm;
    normal = processing.normal;
    open = processing.open;
    openStream = processing.openStream;
    ortho = processing.ortho;
    parseByte = processing.parseByte;
    perspective = processing.perspective;
    point = processing.point;
    pointLight = processing.pointLight;
    popMatrix = processing.popMatrix;
    popStyle = processing.popStyle;
    pow = processing.pow;
    print = processing.print;
    printCamera = processing.printCamera;
    printMatrix = processing.printMatrix;
    printProjection = processing.printProjection;
    println = processing.println;
    pushMatrix = processing.pushMatrix;
    pushStyle = processing.pushStyle;
    quad = processing.quad;
    quadraticVertex = processing.quadraticVertex;
    radians = processing.radians;
    random = processing.random;
    randomSeed = processing.randomSeed;
    rect = processing.rect;
    rectMode = processing.rectMode;
    red = processing.red;
    redraw = processing.redraw;
    requestImage = processing.requestImage;
    resetMatrix = processing.resetMatrix;
    resetShader = processing.resetShader;
    reverse = processing.reverse;
    rotate = processing.rotate;
    rotateX = processing.rotateX;
    rotateY = processing.rotateY;
    rotateZ = processing.rotateZ;
    round = processing.round;
    saturation = processing.saturation;
    save = processing.save;
    saveBytes = processing.saveBytes;
    saveFile = processing.saveFile;
    saveFrame = processing.saveFrame;
    savePath = processing.savePath;
    saveStream = processing.saveStream;
    saveStrings = processing.saveStrings;
    saveType = processing.saveType;
    scale = processing.scale;
    screenX = processing.screenX;
    screenY = processing.screenY;
    screenZ = processing.screenZ;
    second = processing.second;
    selectFolder = processing.selectFolder;
    selectInput = processing.selectInput;
    selectOutput = processing.selectOutput;
    set = processing.set;
    setup = processing.setup;
    shader = processing.shader;
    shape = processing.shape;
    shapeMode = processing.shapeMode;
    shearX = processing.shearX;
    shearY = processing.shearY;
    shininess = processing.shininess;
    shorten = processing.shorten;
    sin = processing.sin;
    size = processing.size; // carefull, this resets to the default
    sketchFile = processing.sketchFile;
    sketchPath = processing.sketchPath;
    smooth = processing.smooth;
    sort = processing.sort;
    specular = processing.specular;
    sphere = processing.sphere;
    sphereDetail = processing.sphereDetail;
    splice = processing.splice;
    split = processing.split;
    splitTokens = processing.splitTokens;
    spotLight = processing.spotLight;
    sq = processing.sq;
    sqrt = processing.sqrt;
    start = processing.start;
    stop = processing.stop;
    stroke = processing.stroke;
    strokeCap = processing.strokeCap;
    strokeJoin = processing.strokeJoin;
    strokeWeight = processing.strokeWeight;
    subset = processing.subset;
    tan = processing.tan;
    text = processing.text;
    textAlign = processing.textAlign;
    textAscent = processing.textAscent;
    textDescent = processing.textDescent;
    textFont = processing.textFont;
    textLeading = processing.textLeading;
    textMode = processing.textMode;
    textSize = processing.textSize;
    textWidth = processing.textWidth;
    texture = processing.texture;
    textureMode = processing.textureMode;
    tint = processing.tint;
    translate = processing.translate;
    triangle = processing.triangle;
    trim = processing.trim;
    unbinary = processing.unbinary;
    unhex = processing.unhex;
    updatePixels = processing.updatePixels;
    vertex = processing.vertex;
    year = processing.year;
    /* + + + + + + + + + + + + +
     + variables
     + + + + + + + + + + + + + */
    this.__defineGetter__('displayHeight',function(){return processing.displayHeight});
    this.__defineGetter__('displayWidth',function(){return processing.displayWidth});
    this.__defineGetter__('frameCount',function(){return processing.frameCount});
    this.__defineGetter__('frameRate',function(){return processing.frameRate});
    this.__defineGetter__('height',function(){return processing.height});
    this.__defineGetter__('key',function(){return processing.key});
    this.__defineGetter__('keyCode',function(){return processing.keyCode});
    this.__defineGetter__('keyPressed',function(){return processing.keyPressed});
    this.__defineGetter__('mousePressed',function(){return processing.mousePressed});
    this.__defineGetter__('mouseX',function(){return processing.mouseX});
    this.__defineGetter__('mouseY',function(){return processing.mouseY});
    this.__defineGetter__('online',function(){return true});
    this.__defineGetter__('pixels',function(){return processing.pixels});
    this.__defineGetter__('pmouseX',function(){return processing.pmouseX});
    this.__defineGetter__('pmouseY',function(){return processing.pmouseY});
    this.__defineGetter__('screenHeight',function(){return processing.screenHeight});
    this.__defineGetter__('screenWidth',function(){return processing.screenWidth});
    this.__defineGetter__('width',function(){return processing.width});
};
