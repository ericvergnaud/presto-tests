define proto as: abstract method returning: Text

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define name as: Text attribute

define Test as: category with attribute: name, and methods:

	define test as: method receiving: Text prefix doing:
		x = 150
		define subTest as: method doing:
			return prefix + name + "/" + x
		return Method: subTest

define printMethod as: method receiving: proto doing:
	s = invoke: proto
	print s
	
define main as: method receiving: Text{} options doing:
	i = Test with "name" as name
	m = i.test with "prefix:" as prefix
	printMethod m
