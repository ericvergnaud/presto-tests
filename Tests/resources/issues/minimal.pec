define print as: native method receiving: Text value doing:
	Java: System.out.println(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define mainWithCmdLine as: method receiving: Text{} options doing:
	for each o in options:
		print "key:" + o.key
		print "value:" + o.value

define mainNoCmdLine as: method doing:
	print "Hello, this is main with no arguments"

