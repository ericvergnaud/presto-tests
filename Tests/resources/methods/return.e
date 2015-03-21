define name as: Text attribute

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define getName as: method receiving: name doing:
	return "name=" + name

define main as: method receiving: Text{} options doing:
	s = getName with "John" as name
	print s
