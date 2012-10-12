
/*
 |  Automatically added code to allow for Processing API to "just work"
 |  ... as it removes the need for "@" signs everywhere. A call to 
 |  injectProcessingApi(this) is being added to top of your setup() method.
 */
var injectProcessingApi = function(klass,processing) {
    console.log({
        context: this,
        receiver_class: klass,
        processing: processing,
        processing_typeof: typeof processing,
        processing_proto: processing.prototype,
        processing_constructor: processing.constructor
    });
    /* + + + + + + + + + + + + +
     + constants
     + + + + + + + + + + + + + */
    klass.ADD = processing.ADD;
    klass.ALIGN_CENTER = processing.ALIGN_CENTER;
    klass.ALIGN_LEFT = processing.ALIGN_LEFT;
    klass.ALIGN_RIGHT = processing.ALIGN_RIGHT;
    klass.ALPHA = processing.ALPHA;
    klass.ALPHA_MASK = processing.ALPHA_MASK;
    klass.ALT = processing.ALT;
    klass.AMBIENT = processing.AMBIENT;
    klass.ARGB = processing.ARGB;
    klass.ARROW = processing.ARROW;
    klass.BACKSPACE = processing.BACKSPACE;
    klass.BASELINE = processing.BASELINE;
    klass.BEVEL = processing.BEVEL;
    klass.BLEND = processing.BLEND;
    klass.BLUE_MASK = processing.BLUE_MASK;
    klass.BLUR = processing.BLUR;
    klass.BOTTOM = processing.BOTTOM;
    klass.BURN = processing.BURN;
    klass.CENTER = processing.CENTER;
    klass.CHATTER = processing.CHATTER;
    klass.CLOSE = processing.CLOSE;
    klass.CMYK = processing.CMYK;
    klass.CODED = processing.CODED;
    klass.COMPLAINT = processing.COMPLAINT;
    klass.COMPONENT = processing.COMPONENT;
    klass.COMPOSITE = processing.COMPOSITE;
    klass.CONCAVE_POLYGON = processing.CONCAVE_POLYGON;
    klass.CONTROL = processing.CONTROL;
    klass.CONVEX_POLYGON = processing.CONVEX_POLYGON;
    klass.CORNER = processing.CORNER;
    klass.CORNERS = processing.CORNERS;
    klass.CROSS = processing.CROSS;
    klass.CUSTOM = processing.CUSTOM;
    klass.DARKEST = processing.DARKEST;
    klass.DEGREES = processing.DEGREES;
    klass.DEG_TO_RAD = processing.DEG_TO_RAD;
    klass.DELETE = processing.DELETE;
    klass.DIAMETER = processing.DIAMETER;
    klass.DIFFERENCE = processing.DIFFERENCE;
    klass.DIFFUSE = processing.DIFFUSE;
    klass.DILATE = processing.DILATE;
    klass.DIRECTIONAL = processing.DIRECTIONAL;
    klass.DISABLED = processing.DISABLED;
    klass.DODGE = processing.DODGE;
    klass.DOWN = processing.DOWN;
    klass.DXF = processing.DXF;
    klass.ENTER = processing.ENTER;
    klass.EPSILON = processing.EPSILON;
    klass.ERODE = processing.ERODE;
    klass.ESC = processing.ESC;
    klass.EXCLUSION = processing.EXCLUSION;
    klass.GIF = processing.GIF;
    klass.GRAY = processing.GRAY;
    klass.GREEN_MASK = processing.GREEN_MASK;
    klass.GROUP = processing.GROUP;
    klass.HALF = processing.HALF;
    klass.HALF_PI = processing.HALF_PI;
    klass.HAND = processing.HAND;
    klass.HARD_LIGHT = processing.HARD_LIGHT;
    klass.HINT_COUNT = processing.HINT_COUNT;
    klass.HSB = processing.HSB;
    klass.IMAGE = processing.IMAGE;
    klass.INVERT = processing.INVERT;
    klass.JAVA2D = processing.JAVA2D;
    klass.JPEG = processing.JPEG;
    klass.LEFT = processing.LEFT;
    klass.LIGHTEST = processing.LIGHTEST;
    klass.LINES = processing.LINES;
    klass.LINUX = processing.LINUX;
    klass.MACOSX = processing.MACOSX;
    klass.MAX_FLOAT = processing.MAX_FLOAT;
    klass.MAX_INT = processing.MAX_INT;
    klass.MITER = processing.MITER;
    klass.MODEL = processing.MODEL;
    klass.MOVE = processing.MOVE;
    klass.MULTIPLY = processing.MULTIPLY;
    klass.NORMAL = processing.NORMAL;
    klass.NORMALIZED = processing.NORMALIZED;
    klass.NO_DEPTH_TEST = processing.NO_DEPTH_TEST;
    klass.NTSC = processing.NTSC;
    klass.ONE = processing.ONE;
    klass.OPAQUE = processing.OPAQUE;
    klass.OPEN = processing.OPEN;
    klass.OPENGL = processing.OPENGL;
    klass.ORTHOGRAPHIC = processing.ORTHOGRAPHIC;
    klass.OVERLAY = processing.OVERLAY;
    klass.P2D = processing.P2D;
    klass.P3D = processing.P3D;
    klass.PAL = processing.PAL;
    klass.PDF = processing.PDF;
    klass.PERSPECTIVE = processing.PERSPECTIVE;
    klass.PI = processing.PI;
    klass.PIXEL_CENTER = processing.PIXEL_CENTER;
    klass.POINT = processing.POINT;
    klass.POINTS = processing.POINTS;
    klass.POSTERIZE = processing.POSTERIZE;
    klass.PROBLEM = processing.PROBLEM;
    klass.PROJECT = processing.PROJECT;
    klass.QUADS = processing.QUADS;
    klass.QUAD_STRIP = processing.QUAD_STRIP;
    klass.QUARTER_PI = processing.QUARTER_PI;
    klass.RADIANS = processing.RADIANS;
    klass.RADIUS = processing.RADIUS;
    klass.RAD_TO_DEG = processing.RAD_TO_DEG;
    klass.RED_MASK = processing.RED_MASK;
    klass.REPLACE = processing.REPLACE;
    klass.RETURN = processing.RETURN;
    klass.RGB = processing.RGB;
    klass.RIGHT = processing.RIGHT;
    klass.ROUND = processing.ROUND;
    klass.SCREEN = processing.SCREEN;
    klass.SECAM = processing.SECAM;
    klass.SHIFT = processing.SHIFT;
    klass.SOFT_LIGHT = processing.SOFT_LIGHT;
    klass.SPECULAR = processing.SPECULAR;
    klass.SQUARE = processing.SQUARE;
    klass.SUBTRACT = processing.SUBTRACT;
    klass.SVIDEO = processing.SVIDEO;
    klass.TAB = processing.TAB;
    klass.TARGA = processing.TARGA;
    klass.TEXT = processing.TEXT;
    klass.TFF = processing.TFF;
    klass.THIRD_PI = processing.THIRD_PI;
    klass.THRESHOLD = processing.THRESHOLD;
    klass.TIFF = processing.TIFF;
    klass.TOP = processing.TOP;
    klass.TRIANGLES = processing.TRIANGLES;
    klass.TRIANGLE_FAN = processing.TRIANGLE_FAN;
    klass.TRIANGLE_STRIP = processing.TRIANGLE_STRIP;
    klass.TUNER = processing.TUNER;
    klass.TWO = processing.TWO;
    klass.TWO_PI = processing.TWO_PI;
    klass.UP = processing.UP;
    klass.WAIT = processing.WAIT;
    klass.WHITESPACE = processing.WHITESPACE;
    klass.XML = processing.XML;
    /* + + + + + + + + + + + + +
     + classes
     + + + + + + + + + + + + + */
    klass.ArrayList = processing.ArrayList;
    klass.BufferedReader = processing.BufferedReader;
    klass.Character = processing.Character;
    klass.HashMap = processing.HashMap;
    klass.Integer = processing.Integer;
    klass.PFont = processing.PFont;
    klass.PGraphics = processing.PGraphics;
    klass.PImage = processing.PImage;
    klass.PShader = processing.PShader;
    klass.PShape = processing.PShape;
    klass.PVector = processing.PVector;
    klass.PrintWriter = processing.PrintWriter;
    klass.StringBuffer = processing.StringBuffer;
    /* + + + + + + + + + + + + +
     + functions
     + + + + + + + + + + + + + */
    klass.abs = processing.abs;
    klass.acos = processing.acos;
    klass.addChild = processing.addChild;
    klass.alpha = processing.alpha;
    klass.ambient = processing.ambient;
    klass.ambientLight = processing.ambientLight;
    klass.append = processing.append;
    klass.applyMatrix = processing.applyMatrix;
    klass.arc = processing.arc;
    klass.asin = processing.asin;
    klass.atan = processing.atan;
    klass.atan2 = processing.atan2;
    klass.background = processing.background;
    klass.beginCamera = processing.beginCamera;
    klass.beginContour = processing.beginContour;
    klass.beginRaw = processing.beginRaw;
    klass.beginRecord = processing.beginRecord;
    klass.beginShape = processing.beginShape;
    klass.bezier = processing.bezier;
    klass.bezierDetail = processing.bezierDetail;
    klass.bezierPoint = processing.bezierPoint;
    klass.bezierTangent = processing.bezierTangent;
    klass.bezierVertex = processing.bezierVertex;
    klass.binary = processing.binary;
    klass.bind = processing.bind;
    klass.blend = processing.blend;
    klass.blendColor = processing.blendColor;
    klass.blendMode = processing.blendMode;
    klass.blue = processing.blue;
    klass.box = processing.box;
    klass.breakShape = processing.breakShape;
    klass.brightness = processing.brightness;
    klass.cache = processing.cache;
    klass.camera = processing.camera;
    klass.ceil = processing.ceil;
    klass.clip = processing.clip;
    klass.color = processing.color;
    klass.colorMode = processing.colorMode;
    klass.concat = processing.concat;
    klass.constrain = processing.constrain;
    klass.copy = processing.copy;
    klass.cos = processing.cos;
    klass.createFont = processing.createFont;
    klass.createGraphics = processing.createGraphics;
    klass.createImage = processing.createImage;
    klass.createInput = processing.createInput;
    klass.createOutput = processing.createOutput;
    klass.createPath = processing.createPath;
    klass.createReader = processing.createReader;
    klass.createShape = processing.createShape;
    klass.createWriter = processing.createWriter;
    klass.cursor = processing.cursor;
    klass.curve = processing.curve;
    klass.curveDetail = processing.curveDetail;
    klass.curvePoint = processing.curvePoint;
    klass.curveTangent = processing.curveTangent;
    klass.curveTightness = processing.curveTightness;
    klass.curveVertex = processing.curveVertex;
    klass.day = processing.day;
    klass.degrees = processing.degrees;
    klass.directionalLight = processing.directionalLight;
    klass.dist = processing.dist;
    klass.draw = processing.draw;
    klass.ellipse = processing.ellipse;
    klass.ellipseMode = processing.ellipseMode;
    klass.emissive = processing.emissive;
    klass.end = processing.end;
    klass.endCamera = processing.endCamera;
    klass.endContour = processing.endContour;
    klass.endRaw = processing.endRaw;
    klass.endRecord = processing.endRecord;
    klass.endShape = processing.endShape;
    klass.exit = processing.exit;
    klass.exp = processing.exp;
    klass.expand = processing.expand;
    klass.fill = processing.fill;
    klass.filter = processing.filter;
    klass.floor = processing.floor;
    klass.focused = processing.focused;
    klass.frameRate = processing.frameRate;
    klass.frustum = processing.frustum;
    klass.get = processing.get;
    klass.green = processing.green;
    klass.hex = processing.hex;
    klass.hint = processing.hint;
    klass.hour = processing.hour;
    klass.hue = processing.hue;
    klass.image = processing.image;
    klass.imageMode = processing.imageMode;
    klass.join = processing.join;
    klass.keyPressed = processing.keyPressed;
    klass.keyReleased = processing.keyReleased;
    klass.keyTyped = processing.keyTyped;
    klass.lerp = processing.lerp;
    klass.lerpColor = processing.lerpColor;
    klass.lightFalloff = processing.lightFalloff;
    klass.lightSpecular = processing.lightSpecular;
    klass.lights = processing.lights;
    klass.line = processing.line;
    klass.loadBytes = processing.loadBytes;
    klass.loadFont = processing.loadFont;
    klass.loadImage = processing.loadImage;
    klass.loadMatrix = processing.loadMatrix;
    klass.loadPixels = processing.loadPixels;
    klass.loadShader = processing.loadShader;
    klass.loadShape = processing.loadShape;
    klass.loadStrings = processing.loadStrings;
    klass.loadType = processing.loadType;
    klass.log = processing.log;
    klass.loop = processing.loop;
    klass.mag = processing.mag;
    klass.map = processing.map;
    klass.match = processing.match;
    klass.matchAll = processing.matchAll;
    klass.max = processing.max;
    klass.millis = processing.millis;
    klass.min = processing.min;
    klass.minute = processing.minute;
    klass.modelX = processing.modelX;
    klass.modelY = processing.modelY;
    klass.modelZ = processing.modelZ;
    klass.month = processing.month;
    klass.mouseButton = processing.mouseButton;
    klass.mouseClicked = processing.mouseClicked;
    klass.mouseDragged = processing.mouseDragged;
    klass.mouseMoved = processing.mouseMoved;
    klass.mousePressed = processing.mousePressed;
    klass.mouseReleased = processing.mouseReleased;
    klass.nf = processing.nf;
    klass.nfc = processing.nfc;
    klass.nfp = processing.nfp;
    klass.nfs = processing.nfs;
    klass.noClip = processing.noClip;
    klass.noCursor = processing.noCursor;
    klass.noFill = processing.noFill;
    klass.noHint = processing.noHint;
    klass.noLights = processing.noLights;
    klass.noLoop = processing.noLoop;
    klass.noSmooth = processing.noSmooth;
    klass.noStroke = processing.noStroke;
    klass.noTint = processing.noTint;
    klass.noise = processing.noise;
    klass.noiseDetail = processing.noiseDetail;
    klass.noiseSeed = processing.noiseSeed;
    klass.norm = processing.norm;
    klass.normal = processing.normal;
    klass.open = processing.open;
    klass.openStream = processing.openStream;
    klass.ortho = processing.ortho;
    klass.perspective = processing.perspective;
    klass.point = processing.point;
    klass.pointLight = processing.pointLight;
    klass.popMatrix = processing.popMatrix;
    klass.popStyle = processing.popStyle;
    klass.pow = processing.pow;
    klass.print = processing.print;
    klass.printCamera = processing.printCamera;
    klass.printMatrix = processing.printMatrix;
    klass.printProjection = processing.printProjection;
    klass.println = processing.println;
    klass.pushMatrix = processing.pushMatrix;
    klass.pushStyle = processing.pushStyle;
    klass.quad = processing.quad;
    klass.quadraticVertex = processing.quadraticVertex;
    klass.radians = processing.radians;
    klass.random = processing.random;
    klass.randomSeed = processing.randomSeed;
    klass.rect = processing.rect;
    klass.rectMode = processing.rectMode;
    klass.red = processing.red;
    klass.redraw = processing.redraw;
    klass.requestImage = processing.requestImage;
    klass.resetMatrix = processing.resetMatrix;
    klass.resetShader = processing.resetShader;
    klass.reverse = processing.reverse;
    klass.rotate = processing.rotate;
    klass.rotateX = processing.rotateX;
    klass.rotateY = processing.rotateY;
    klass.rotateZ = processing.rotateZ;
    klass.round = processing.round;
    klass.saturation = processing.saturation;
    klass.save = processing.save;
    klass.saveBytes = processing.saveBytes;
    klass.saveFile = processing.saveFile;
    klass.saveFrame = processing.saveFrame;
    klass.savePath = processing.savePath;
    klass.saveStream = processing.saveStream;
    klass.saveStrings = processing.saveStrings;
    klass.saveType = processing.saveType;
    klass.scale = processing.scale;
    klass.screenX = processing.screenX;
    klass.screenY = processing.screenY;
    klass.screenZ = processing.screenZ;
    klass.second = processing.second;
    klass.selectFolder = processing.selectFolder;
    klass.selectInput = processing.selectInput;
    klass.selectOutput = processing.selectOutput;
    klass.set = processing.set;
    klass.setup = processing.setup;
    klass.shader = processing.shader;
    klass.shape = processing.shape;
    klass.shapeMode = processing.shapeMode;
    klass.shearX = processing.shearX;
    klass.shearY = processing.shearY;
    klass.shininess = processing.shininess;
    klass.shorten = processing.shorten;
    klass.sin = processing.sin;
    klass.size = processing.size;
    klass.sketchFile = processing.sketchFile;
    klass.sketchPath = processing.sketchPath;
    klass.smooth = processing.smooth;
    klass.sort = processing.sort;
    klass.specular = processing.specular;
    klass.sphere = processing.sphere;
    klass.sphereDetail = processing.sphereDetail;
    klass.splice = processing.splice;
    klass.split = processing.split;
    klass.splitTokens = processing.splitTokens;
    klass.spotLight = processing.spotLight;
    klass.sq = processing.sq;
    klass.sqrt = processing.sqrt;
    klass.start = processing.start;
    klass.stop = processing.stop;
    klass.stroke = processing.stroke;
    klass.strokeCap = processing.strokeCap;
    klass.strokeJoin = processing.strokeJoin;
    klass.strokeWeight = processing.strokeWeight;
    klass.subset = processing.subset;
    klass.tan = processing.tan;
    klass.text = processing.text;
    klass.textAlign = processing.textAlign;
    klass.textAscent = processing.textAscent;
    klass.textDescent = processing.textDescent;
    klass.textFont = processing.textFont;
    klass.textLeading = processing.textLeading;
    klass.textMode = processing.textMode;
    klass.textSize = processing.textSize;
    klass.textWidth = processing.textWidth;
    klass.texture = processing.texture;
    klass.textureMode = processing.textureMode;
    klass.tint = processing.tint;
    klass.translate = processing.translate;
    klass.triangle = processing.triangle;
    klass.trim = processing.trim;
    klass.unbinary = processing.unbinary;
    klass.unhex = processing.unhex;
    klass.updatePixels = processing.updatePixels;
    klass.vertex = processing.vertex;
    klass.year = processing.year;
    /* + + + + + + + + + + + + +
     + variables
     + + + + + + + + + + + + + */
    klass.__defineGetter__('displayHeight',function(){return processing.displayHeight})
    klass.__defineGetter__('displayWidth',function(){return processing.displayWidth})
    klass.__defineGetter__('frameCount',function(){return processing.frameCount})
    klass.__defineGetter__('frameRate',function(){return processing.frameRate})
    klass.__defineGetter__('height',function(){return processing.height})
    klass.__defineGetter__('key',function(){return processing.key})
    klass.__defineGetter__('keyCode',function(){return processing.keyCode})
    klass.__defineGetter__('keyPressed',function(){return processing.keyPressed})
    klass.__defineGetter__('mousePressed',function(){return processing.mousePressed})
    klass.__defineGetter__('mouseX',function(){return processing.mouseX})
    klass.__defineGetter__('mouseY',function(){return processing.mouseY})
    klass.__defineGetter__('online',function(){return true})
    klass.__defineGetter__('pixels',function(){return processing.pixels})
    klass.__defineGetter__('pmouseX',function(){return processing.pmouseX})
    klass.__defineGetter__('pmouseY',function(){return processing.pmouseY})
    klass.__defineGetter__('screenHeight',function(){return processing.screenHeight})
    klass.__defineGetter__('screenWidth',function(){return processing.screenWidth})
    klass.__defineGetter__('width',function(){return processing.width})
};