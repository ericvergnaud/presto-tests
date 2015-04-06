define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define main as: method receiving: Text{} options doing:
	list = [ 1, 2, 3, 4, 5, 6 ]
	even_nums = fetch any item from list where item % 2 = 0
	print "" + even_nums
