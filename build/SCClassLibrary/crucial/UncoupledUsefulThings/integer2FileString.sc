
/*
	converts any signed integer (such as an identityHash) into a base 62
	string for unique file name usage.
*/

+ Integer {

	asFileSafeChar {
		^(#[ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z","_","*","#","-","+","~","$" ]).at(this)	
	}
	/*  
		extension methods crash if called recursively.
		james is working on a fix.
		
	asFileSafeString {
		var output,msd,lsd;
		if(this < 62,{
			if(this.isNegative,{
				^("-" ++ this.neg.asFileSafeString)
			},{
				^this.asFileSafeChar
			})
		},{
			msd = floor(this / 62.0);
			lsd = this - (msd * 62.0);
			if(msd >= 62,{
				^msd.asFileSafeString ++ lsd.asInteger.asFileSafeChar
			},{
				^msd.asInteger.asFileSafeChar ++ lsd.asInteger.asFileSafeChar
			})
		})
	}
	*/
	asFileSafeString {
		var output = "", val, msd, lsd;
		val = this.abs;
		while ({ val != 0 },{
			msd = floor(val / 69).asInteger;
			lsd = val - (msd * 69);
			output = lsd.asFileSafeChar ++ output;
			val = msd;
		});
		if (this < 0, { ^"-" ++ output });
		^output
	}
	
}

+ Object {
	
	asFileSafeString {
		^this.asCompileString.hash.asFileSafeString
	}
	
}