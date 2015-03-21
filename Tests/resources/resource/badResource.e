define path as: Text attribute

define File as: native resource with attribute: path, and mappings:

	define category mappings as:
		Java: utils.MyResource
		C#: utils.MyResource
	
	
define main as: method receiving: Text{} options doing:
	f = File with "xyz" as path
