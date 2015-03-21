define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define main as: method receiving: Text{} options doing:
	print "/" + ('2012-10-04T10:10:08' <= '2012-10-04T10:10:09')
	print "/" + ('2012-10-05T10:10:10' <= '2012-10-05T10:10:10')
	print "/" + ('2012-10-05T10:10:11' <= '2012-10-05T10:10:10')
	