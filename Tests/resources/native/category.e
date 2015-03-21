define id as: Text attribute 
define name as: Text attribute
define display as: Text attribute

define MyClass as: native category with attributes: id, name and display, and mappings:

	define category mappings as:
		Java: presto.runtime.utils.MyClass
		C#: presto.runtime.utils.MyClass
		Python2: MyClass from module: presto.runtime.utils.MyClass
		Python3: MyClass from module: presto.runtime.utils.MyClass
		JavaScript: MyClass from module: test/user/MyClass.js

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define show as: native method receiving: MyClass value doing:
	Java: value.printDisplay();
	C#: value.printDisplay();
	Python2: value.printDisplay()
	Python3: value.printDisplay()
	JavaScript: value.printDisplay();

define main as: method receiving: Text{} options doing:
	c = MyClass with "1" as id and "John" as name
	print "print:" + c.display + ","
	show c
	
