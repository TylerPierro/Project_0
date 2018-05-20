package gringotts.launcher;

import gringotts.prompts.MainMenuPrompt;
import gringotts.prompts.Prompt;

public class Home	{
	//public static User user;
	public final static String FILE_LOCATION = "src/main/resources/";
	
    public static void main(String[] args)	{
    	System.out.println("Welcome to Gringott's Bank\nThe safest place on Earth");
    	
    	System.out.println(
"                                     `.-://+ooosssoso+o++/:--.`                                     \n"+
"                               `-:/oysyyyysssooo++++++ooosoossoso::-`                               \n"+
"                           .:+sssyso+/:-.``  ``......``` ``.-:/+ssssss/-.                           \n"+
"                       `-+syyss+:-` `.-:///+////:::::://:///::.```.:+ossso+-`                       \n"+
"                    `-+syys+:` `.:/+/:-.``   :`    `/- `-` `..-:///-.`.-+syss+-`                    \n"+
"                  .+syys/-` .://:..--.::`   `s/    `os. +   .s: .-.-/+/-``-/+sss/.                  \n"+
"                -oyys+-` .:+:-`   `/o``s-   .:s:   .-/o`:   ++.-:`    `:/+-``-+ssso-                \n"+
"              -osys/. `:+/.`       `s+-+/-  :-:s.  :` /o.  :s+s`         `:++-`./sys+:              \n"+
"            -oyss:` `/+:`.-::`      -s. -s` /  /o` :`  +` `s--y`      `:/-.-./+-``:ssso-            \n"+
"          `+sss/` `/+:``:s- -`       ++`::  .  ``  `   `  -/`.s.     -s/` .s  `/+:``/sys+`          \n"+
"         -oss+.  :o:`    -o//:``.    .``                     `.`    .o.  -s:   :./+- .+syo-         \n"+
"       `/yys-  .+/``:/.   `/+- `/.                     .-           :: ./o-  ./s/-.o+` :sss/`       \n"+
"      `+syo.  :o:`:-`-+/-`  .o:.`            ````````  `s+`          .--.` .+o/. ::`+o` .oyyo`      \n"+
"     `osy+` `/o. `-//-:./+:. .        `.-:++oooosssooo+/sso-            `-++.-:. -` -yo  `+syo`     \n"+
"    `oyy+` `+o````  ./+:`./-      `./+osoo+/::::::://+osyssy/`           .-  `    `:sys   `+yso`    \n"+
"   `osy+` `+o`.+-.`   `-o-     `-+oo/:-----::-:::-.`  ``-/oyys-                   .yyy:     +yyo`   \n"+
"   +yyo`  +s`.//++:.`   `    `:+o:..-:::--.`    `.-//.    `-oyy/`                 /syy:     `oyy+   \n"+
"  -sys.  -y- .--`.-/++/`    :so-`.///:--..--:.`     -o/`    `+yys-                 `./s-     .syy-  \n"+
" `syy:   +s.         :.   `/y/`.+///.        .:-`     -/:.`   oyyy/`                  .o      /yys` \n"+
" :sso    /ys+/      ``.-:/+++--::::-.---.`     `--.     `..`  `syyyo.                  .       osy: \n"+
" syy:     :yyy:   `/+/:.``   `..-:-:-.```--       ``    .-..-: -/+oss:..``             .       :yys \n"+
".yys`      oys/`    `.-:oo++/::.   `.:so:            -::-.-/++        ``...---`        +:      `yyy.\n"+
"/yyo      `s/`         .yy: `.-:+:    :ys-          `.  `-:--.     `.-...`   `::       oy-`     osy/\n"+
"oyy/      -o           /yy:     `/+  `osso.                    - -/-..-:::-----.  `.:+oyyyso/-` /sso\n"+
"syy:       -           oyso       +/   ossso+:`                //- +-.`            ``..:yo      :sys\n"+
"syy:        `          oyys-      `s:  ..`   `:`                  .:+                   /+      :yys\n"+
"syy:       .o`         +yyys-      .s+-              `.---..    -//-:+:                  -      :yys\n"+
"oyy/       -y+`        :yyyys/`     .+ss++///++`    /ssssyssso+/:-:+ss`                         /syo\n"+
"/yyo   .-/+syyss+/.    `yyyyyys:`     .:////:.     :syyyo///+sysssss/`          `o+:-  `...     oyy/\n"+
"-ysy`  `....oy-         :yyyyyyys+:.               -syso`    .sssy:`            /.   `+so//+.  `yyy-\n"+
" ssy:       `s.          +yyyyyysys:-----.``        +yso`     /yss:             o`  -ss:   `o  :yys \n"+
" :yys        .`          `osyyyyyyys-  ``.--:+-      ./+/-    oyss+             .+++o+.    :/  syy: \n"+
" `syy:         `-:/+/:.   `/ssyyyyyyy+.   .:/-              `/syyys           .-.````  .:/+:  :yys` \n"+
"  -yys.      -oso/-.``--    :syyyyyyyys+//-`               -os/oyyy-         -+--:-      ``` .yyy-  \n"+
"   +yyo`   `oo:.`     `s`    `/syyyyyyysyo/-`          `./oys: /syys.       :oso:.`         `oyy+   \n"+
"   `oys+   -/   `/-  .:.```    `:osyyyyyyyyyso+/::::://osys+. .osyyys:.`   :o`.:ooo/-`      +yyo`   \n"+
"    `syy+  `:::+oo+`     .o/`     `-/osyyyyyyyyyyyysssso+:` .:+o+///:-.` -.`.:-  `-+oo+:/ `+yys`    \n"+
"     .oss/`  //-`      `/so-::`       `.-://++oo+++/:-.`    ``        `-+::/. `     `.o+``+yyo.     \n"+
"      `/+oo.         `:so-  .s/                                     `::+y+.`-         . .oyyo`      \n"+
"        .-oo-      `:oo-/--/so.  .-``                               `o- -os/`          :syy+`       \n"+
"         .osy+.  .:so-  -y---`    os/-                       `--:/:.  -. `:so:`      .+sys:         \n"+
"          `+yss/` ./`  -s+       /s-    -+:   .`    ````.`   ++  `+s/`     `:so--` `/sys+`          \n"+
"            -oyys:`   -o-       /s-     :sy. `+:`  :+.../:   ss`   +y+       .o+-`:syyo-            \n"+
"              :osso/` `.       +s-      o-yo  o`  -y-    :   /y+    +y:      `.`/syyo:              \n"+
"               `-/oys+-`    ``+s-      ./ +y:./   +y-  ````   /y/   `s/     `-+syso:`               \n"+
"                  .+oyys/-``.:o+`      /. `ss/`   /y/  .oy:    -s+. `+-  `-/syys+.                  \n"+
"                    `:oyyys/-` `.      +   -y+    `ss   :y-      -:--.`:+syss+:`                    \n"+
"                       `:+syyyo+:.`  `-/`   +.     .o/``/s/      `.:+syyss+-`                       \n"+
"                           .:+syyyss+/:-.`  ``       ...``..-:/osyssss+:.                           \n"+
"                               .-/+ssssssyyssoooo+++ooossyyyyyyso+/-.                               \n"+
"                                     `.-:/+oososyyyyssooo+/::-`                                     \n");
    	Prompt currentPrompt = new MainMenuPrompt();
    	while(true)	{
        	currentPrompt = currentPrompt.run();
        }
    }
}
