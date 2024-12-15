package ua.mei.pfu.api;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class TextResource {
    public static final Set<TextResource> resources = new HashSet<>();

    public final int ascent;

    public final FontResourceManager manager;
    public final String json;

    public final Identifier identifier;

    protected TextResource(Identifier identifier, int ascent, FontResourceManager manager) {
        this.ascent = ascent;
        this.manager = manager;

        this.json = String.format("""
                {
                    "providers": [
                        {
                            "type": "reference",
                            "id": "minecraft:include/space"
                        },
                        {
                            "type": "bitmap",
                            "file": "minecraft:font/nonlatin_european.png",
                            "ascent": %s,
                            "chars": [
                                "\\u00a1\\u2030\\u00ad\\u00b7\\u20b4\\u2260\\u00bf\\u00d7\\u00d8\\u00de\\u04bb\\u00f0\\u00f8\\u00fe\\u0391\\u0392",
                                "\\u0393\\u0394\\u0395\\u0396\\u0397\\u0398\\u0399\\u039a\\u039b\\u039c\\u039d\\u039e\\u039f\\u03a0\\u03a1\\u03a3",
                                "\\u03a4\\u03a5\\u03a6\\u03a7\\u03a8\\u03a9\\u03b1\\u03b2\\u03b3\\u03b4\\u03b5\\u03b6\\u03b7\\u03b8\\u03b9\\u03ba",
                                "\\u03bb\\u03bc\\u03bd\\u03be\\u03bf\\u03c0\\u03c1\\u03c2\\u03c3\\u03c4\\u03c5\\u03c6\\u03c7\\u03c8\\u03c9\\u0402",
                                "\\u0405\\u0406\\u0408\\u0409\\u040a\\u040b\\u0410\\u0411\\u0412\\u0413\\u0414\\u0415\\u0416\\u0417\\u0418\\u041a",
                                "\\u041b\\u041c\\u041d\\u041e\\u041f\\u0420\\u0421\\u0422\\u0423\\u0424\\u0425\\u0426\\u0427\\u0428\\u0429\\u042a",
                                "\\u042b\\u042c\\u042d\\u042e\\u042f\\u0430\\u0431\\u0432\\u0433\\u0434\\u0435\\u0436\\u0437\\u0438\\u043a\\u043b",
                                "\\u043c\\u043d\\u043e\\u043f\\u0440\\u0441\\u0442\\u0443\\u0444\\u0445\\u0446\\u0447\\u0448\\u0449\\u044a\\u044b",
                                "\\u044c\\u044d\\u044e\\u044f\\u0454\\u0455\\u0456\\u0458\\u0459\\u045a\\u2013\\u2014\\u2018\\u2019\\u201c\\u201d",
                                "\\u201e\\u2026\\u204a\\u2190\\u2191\\u2192\\u2193\\u21c4\\uff0b\\u018f\\u0259\\u025b\\u026a\\u04ae\\u04af\\u04e8",
                                "\\u04e9\\u02bb\\u02cc\\u037e\\u0138\\u1e9e\\u00df\\u20bd\\u20ac\\u0462\\u0463\\u0474\\u0475\\u04c0\\u0472\\u0473",
                                "\\u2070\\u00b9\\u00b3\\u2074\\u2075\\u2076\\u2077\\u2078\\u2079\\u207a\\u207b\\u207c\\u207d\\u207e\\u2071\\u2122",
                                "\\u0294\\u0295\\u29c8\\u2694\\u2620\\u049a\\u049b\\u0492\\u0493\\u04b0\\u04b1\\u04d8\\u04d9\\u0496\\u0497\\u04a2",
                                "\\u04a3\\u04ba\\u05d0\\u05d1\\u05d2\\u05d3\\u05d4\\u05d5\\u05d6\\u05d7\\u05d8\\u05d9\\u05db\\u05dc\\u05de\\u05dd",
                                "\\u05e0\\u05df\\u05e1\\u05e2\\u05e4\\u05e3\\u05e6\\u05e5\\u05e7\\u05e8\\u00a2\\u00a4\\u00a5\\u00a9\\u00ae\\u00b5",
                                "\\u00b6\\u00bc\\u00bd\\u00be\\u0387\\u2010\\u201a\\u2020\\u2021\\u2022\\u2031\\u2032\\u2033\\u2034\\u2035\\u2036",
                                "\\u2037\\u2039\\u203a\\u203b\\u203c\\u203d\\u2042\\u2048\\u2049\\u204b\\u204e\\u204f\\u2051\\u2052\\u2057\\u2117",
                                "\\u2212\\u2213\\u221e\\u2600\\u2601\\u2608\\u0404\\u2632\\u2635\\u263d\\u2640\\u2642\\u26a5\\u2660\\u2663\\u2665",
                                "\\u2666\\u2669\\u266a\\u266b\\u266c\\u266d\\u266e\\u266f\\u2680\\u2681\\u2682\\u2683\\u2684\\u2685\\u02ac\\u26a1",
                                "\\u26cf\\u2714\\u2744\\u274c\\u2764\\u2b50\\u2e18\\u2e2e\\u2e35\\u2e38\\u2e41\\u2e4b\\u295d\\u1614\\u0190\\u07c8",
                                "\\u03db\\u3125\\u2c6f\\u15fa\\u0186\\u15e1\\u018e\\u2132\\u2141\\ua7b0\\ua780\\u0500\\ua779\\u1d1a\\u27d8\\u2229",
                                "\\u0245\\u2144\\u0250\\u0254\\u01dd\\u025f\\u1d77\\u0265\\u1d09\\u027e\\u029e\\ua781\\u026f\\u0279\\u0287\\u028c",
                                "\\u028d\\u028e\\u0531\\u0532\\u0533\\u0534\\u0536\\u0537\\u0539\\u053a\\u053b\\u053c\\u053d\\u053e\\u053f\\u0540",
                                "\\u0541\\u0542\\u0543\\u0544\\u0545\\u0546\\u0547\\u0548\\u0549\\u054b\\u054c\\u054d\\u054e\\u054f\\u0550\\u0551",
                                "\\u0552\\u0553\\u0554\\u0555\\u0556\\u0559\\u0561\\u0562\\u0563\\u0564\\u0565\\u0566\\u0567\\u0568\\u0569\\u056a",
                                "\\u056b\\u056c\\u056d\\u056e\\u056f\\u0570\\u0571\\u0572\\u0573\\u0574\\u0575\\u0576\\u0577\\u0578\\u0579\\u057a",
                                "\\u057b\\u057c\\u057d\\u057e\\u057f\\u0580\\u0581\\u0582\\u0583\\u0584\\u0585\\u0586\\u0587\\u05e9\\u05ea\\u0538",
                                "\\u055a\\u055b\\u055c\\u055d\\u055e\\u055f\\u0560\\u0588\\u058f\\u00af\\u017f\\u01b7\\u0292\\u01f7\\u01bf\\u021c",
                                "\\u021d\\u0224\\u0225\\u02d9\\ua75a\\ua75b\\u2011\\u214b\\u23cf\\u23e9\\u23ea\\u23ed\\u23ee\\u23ef\\u23f4\\u23f5",
                                "\\u23f6\\u23f7\\u23f8\\u23f9\\u23fa\\u23fb\\u23fc\\u23fd\\u2b58\\u25b2\\u25b6\\u25bc\\u25c0\\u25cf\\u25e6\\u25d8",
                                "\\u2693\\u26e8\\u0132\\u0133\\u01c9\\ua728\\ua729\\ua739\\ua73b\\ufb00\\ufb01\\ufb02\\ufb03\\ufb05\\ufffd\\u0535",
                                "\\u054a\\u16a0\\u16a2\\u16a3\\u16a4\\u16a5\\u16a6\\u16a7\\u16a8\\u16a9\\u16aa\\u16ab\\u16ac\\u16ad\\u16ae\\u16af",
                                "\\u16b0\\u16b1\\u16b2\\u16b3\\u16b4\\u16b6\\u16b7\\u16b8\\u16b9\\u16ba\\u16bb\\u16bc\\u16bd\\u16be\\u16bf\\u16c0",
                                "\\u16c1\\u16c2\\u16c3\\u16c4\\u16c5\\u16c6\\u16c7\\u16c8\\u16c9\\u16ca\\u16cb\\u16cc\\u16cd\\u16ce\\u16cf\\u16d0",
                                "\\u16d1\\u16d2\\u16d3\\u16d4\\u16d5\\u16d6\\u16d7\\u16d8\\u16d9\\u16da\\u16db\\u16dc\\u16dd\\u16de\\u16df\\u16e0",
                                "\\u16e1\\u16e2\\u16e3\\u16e4\\u16e5\\u16e6\\u16e7\\u16e8\\u16e9\\u16ea\\u16eb\\u16ec\\u16ed\\u16ee\\u16ef\\u16f0",
                                "\\u16f1\\u16f2\\u16f3\\u16f4\\u16f5\\u16f6\\u16f7\\u16f8\\u263a\\u263b\\u00a6\\u2639\\u05da\\u05f3\\u05f4\\u05f0",
                                "\\u05f1\\u05f2\\u05be\\u05c3\\u05c6\\u00b4\\u00a8\\u1d00\\u0299\\u1d04\\u1d05\\u1d07\\ua730\\u0262\\u029c\\u1d0a",
                                "\\u1d0b\\u029f\\u1d0d\\u0274\\u1d0f\\u1d18\\ua7af\\u0280\\ua731\\u1d1b\\u1d1c\\u1d20\\u1d21\\u028f\\u1d22\\u00a7",
                                "\\u0271\\u0273\\u0272\\u0288\\u0256\\u0261\\u02a1\\u0255\\u0291\\u0278\\u029d\\u02a2\\u027b\\u0281\\u0266\\u028b",
                                "\\u0270\\u026c\\u026e\\u0298\\u01c0\\u01c3\\u01c2\\u01c1\\u0253\\u0257\\u1d91\\u0284\\u0260\\u029b\\u0267\\u026b",
                                "\\u0268\\u0289\\u028a\\u0258\\u0275\\u0264\\u025c\\u025e\\u0251\\u0252\\u025a\\u025d\\u0181\\u0189\\u0191\\u01a9",
                                "\\u01b2\\u10a0\\u10a1\\u10a2\\u10a3\\u10a4\\u10a5\\u10a6\\u10a7\\u10a8\\u10a9\\u10aa\\u10ab\\u10ac\\u10ad\\u10ae",
                                "\\u10af\\u10b0\\u10b1\\u10b2\\u10b3\\u10b4\\u10b5\\u10b6\\u10b7\\u10b8\\u10b9\\u10ba\\u10bb\\u10bc\\u10bd\\u10be",
                                "\\u10bf\\u10c0\\u10c1\\u10c2\\u10c3\\u10c4\\u10c5\\u10c7\\u10cd\\u10d0\\u10d1\\u10d2\\u10d3\\u10d4\\u10d5\\u10d6",
                                "\\u10d7\\u10d8\\u10d9\\u10da\\u10db\\u10dc\\u10dd\\u10de\\u10df\\u10e0\\u10e1\\u10e2\\u10e3\\u10e4\\u10e5\\u10e6",
                                "\\u10e7\\u10e8\\u10e9\\u10ea\\u10eb\\u10ec\\u10ed\\u10ee\\u10ef\\u10f0\\u10f1\\u10f2\\u10f3\\u10f4\\u10f5\\u10f6",
                                "\\u10f7\\u10f8\\u10f9\\u10fa\\u10fb\\u10fc\\u10fd\\u10fe\\u10ff\\ufb4a\\ufb2b\\ufb4e\\ufb44\\ufb3b\\ufb1f\\ufb1d",
                                "\\ufb4b\\ufb35\\ufb4c\\ufb31\\ua727\\ua726\\u027a\\u2c71\\u02a0\\u0297\\u0296\\u026d\\u0277\\u027f\\u0285\\u0286",
                                "\\u0293\\u029a\\u20aa\\u20be\\u058a\\u2d00\\u2d01\\u2d02\\u2d03\\u2d04\\u2d05\\u2d06\\u2d21\\u2d07\\u2d08\\u2d09",
                                "\\u2d0a\\u2d0b\\u2d0c\\u2d22\\u2d0d\\u2d0e\\u2d0f\\u2d10\\u2d11\\u2d12\\u2d23\\u2d13\\u2d14\\u2d15\\u2d16\\u2d17",
                                "\\u2d18\\u2d19\\u2d1a\\u2d1b\\u2d1c\\u2d1d\\u2d1e\\u2d24\\u2d1f\\u2d20\\u2d25\\u215b\\u215c\\u215d\\u215e\\u2153",
                                "\\u2154\\u2709\\u2602\\u2614\\u2604\\u26c4\\u2603\\u231b\\u231a\\u2690\\u270e\\u2763\\u2664\\u2667\\u2661\\u2662",
                                "\\u26c8\\u2630\\u2631\\u2633\\u2634\\u2636\\u2637\\u2194\\u21d2\\u21cf\\u21d4\\u21f5\\u2200\\u2203\\u2204\\u2209",
                                "\\u220b\\u220c\\u2282\\u2283\\u2284\\u2285\\u2227\\u2228\\u22bb\\u22bc\\u22bd\\u2225\\u2262\\u22c6\\u2211\\u22a4",
                                "\\u22a5\\u22a2\\u22a8\\u2254\\u2201\\u2234\\u2235\\u221b\\u221c\\u2202\\u22c3\\u2286\\u2287\\u25a1\\u25b3\\u25b7",
                                "\\u25bd\\u25c1\\u25c6\\u25c7\\u25cb\\u25ce\\u2606\\u2605\\u2718\\u2080\\u2081\\u2082\\u2083\\u2084\\u2085\\u2086",
                                "\\u2087\\u2088\\u2089\\u208a\\u208b\\u208c\\u208d\\u208e\\u222b\\u222e\\u221d\\u2300\\u2302\\u2318\\u3012\\u027c",
                                "\\u0184\\u0185\\u1e9f\\u023d\\u019a\\u019b\\u0220\\u019e\\u019f\\u01a7\\u01a8\\u01aa\\u01b8\\u01b9\\u01bb\\u01bc",
                                "\\u01bd\\u01be\\u0221\\u0234\\u0235\\u0236\\u023a\\u2c65\\u023b\\u023c\\u0246\\u0247\\u023e\\u2c66\\u0241\\u0242",
                                "\\u0243\\u0244\\u0248\\u0249\\u024a\\u024b\\u024c\\u024d\\u024e\\u024f\\u1e9c\\u1e9d\\u1efc\\u1efd\\u1efe\\u1eff",
                                "\\ua7a8\\ua7a9\\ud800\\udf30\\ud800\\udf31\\ud800\\udf32\\ud800\\udf33\\ud800\\udf34\\ud800\\udf35\\ud800\\udf36\\ud800\\udf37\\ud800\\udf38\\ud800\\udf39\\ud800\\udf3a\\ud800\\udf3b\\ud800\\udf3c\\ud800\\udf3d",
                                "\\ud800\\udf3e\\ud800\\udf3f\\ud800\\udf40\\ud800\\udf41\\ud800\\udf42\\ud800\\udf43\\ud800\\udf44\\ud800\\udf45\\ud800\\udf46\\ud800\\udf47\\ud800\\udf48\\ud800\\udf49\\ud800\\udf4a\\ud83c\\udf27\\ud83d\\udd25\\ud83c\\udf0a",
                                "\\u2150\\u2151\\u2155\\u2156\\u2157\\u2159\\u215a\\u215f\\u2189\\ud83d\\udde1\\ud83c\\udff9\\ud83e\\ude93\\ud83d\\udd31\\ud83c\\udfa3\\ud83e\\uddea\\u2697",
                                "\\u2bea\\u2beb\\u2c6d\\ud83d\\udee1\\u2702\\ud83c\\udf56\\ud83e\\udea3\\ud83d\\udd14\\u23f3\\u2691\\u20a0\\u20a1\\u20a2\\u20a3\\u20a4\\u20a5",
                                "\\u20a6\\u20a9\\u20ab\\u20ad\\u20ae\\u20b0\\u20b1\\u20b2\\u20b3\\u20b5\\u20b6\\u20b7\\u20b8\\u20b9\\u20ba\\u20bb",
                                "\\u20bc\\u20bf\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000"
                            ]
                        },
                        {
                            "type": "bitmap",
                            "file": "minecraft:font/accented.png",
                            "height": 12,
                            "ascent": %s,
                            "chars": [
                                "\\u00c0\\u00c1\\u00c2\\u00c3\\u00c4\\u00c5\\u00c6\\u00c7\\u00c8\\u00c9\\u00ca\\u00cb\\u00cc\\u00cd\\u00ce\\u00cf",
                                "\\u00d0\\u00d1\\u00d2\\u00d3\\u00d4\\u00d5\\u00d6\\u00d9\\u00da\\u00db\\u00dc\\u00dd\\u00e0\\u00e1\\u00e2\\u00e3",
                                "\\u00e4\\u00e5\\u00e6\\u00e7\\u00ec\\u00ed\\u00ee\\u00ef\\u00f1\\u00f2\\u00f3\\u00f4\\u00f5\\u00f6\\u00f9\\u00fa",
                                "\\u00fb\\u00fc\\u00fd\\u00ff\\u0100\\u0101\\u0102\\u0103\\u0104\\u0105\\u0106\\u0107\\u0108\\u0109\\u010a\\u010b",
                                "\\u010c\\u010d\\u010e\\u010f\\u0110\\u0111\\u0112\\u0113\\u0114\\u0115\\u0116\\u0117\\u0118\\u0119\\u011a\\u011b",
                                "\\u011c\\u011d\\u1e20\\u1e21\\u011e\\u011f\\u0120\\u0121\\u0122\\u0123\\u0124\\u0125\\u0126\\u0127\\u0128\\u0129",
                                "\\u012a\\u012b\\u012c\\u012d\\u012e\\u012f\\u0130\\u0131\\u0134\\u0135\\u0136\\u0137\\u0139\\u013a\\u013b\\u013c",
                                "\\u013d\\u013e\\u013f\\u0140\\u0141\\u0142\\u0143\\u0144\\u0145\\u0146\\u0147\\u0148\\u014a\\u014b\\u014c\\u014d",
                                "\\u014e\\u014f\\u0150\\u0151\\u0152\\u0153\\u0154\\u0155\\u0156\\u0157\\u0158\\u0159\\u015a\\u015b\\u015c\\u015d",
                                "\\u015e\\u015f\\u0160\\u0161\\u0162\\u0163\\u0164\\u0165\\u0166\\u0167\\u0168\\u0169\\u016a\\u016b\\u016c\\u016d",
                                "\\u016e\\u016f\\u0170\\u0171\\u0172\\u0173\\u0174\\u0175\\u0176\\u0177\\u0178\\u0179\\u017a\\u017b\\u017c\\u017d",
                                "\\u017e\\u01fc\\u01fd\\u01fe\\u01ff\\u0218\\u0219\\u021a\\u021b\\u0386\\u0388\\u0389\\u038a\\u038c\\u038e\\u038f",
                                "\\u0390\\u03aa\\u03ab\\u03ac\\u03ad\\u03ae\\u03af\\u03b0\\u03ca\\u03cb\\u03cc\\u03cd\\u03ce\\u0400\\u0401\\u0403",
                                "\\u0407\\u040c\\u040d\\u040e\\u0419\\u0439\\u0450\\u0451\\u0452\\u0453\\u0457\\u045b\\u045c\\u045d\\u045e\\u045f",
                                "\\u0490\\u0491\\u1e02\\u1e03\\u1e0a\\u1e0b\\u1e1e\\u1e1f\\u1e22\\u1e23\\u1e30\\u1e31\\u1e40\\u1e41\\u1e56\\u1e57",
                                "\\u1e60\\u1e61\\u1e6a\\u1e6b\\u1e80\\u1e81\\u1e82\\u1e83\\u1e84\\u1e85\\u1ef2\\u1ef3\\u00e8\\u00e9\\u00ea\\u00eb",
                                "\\u0149\\u01e7\\u01eb\\u040f\\u1e0d\\u1e25\\u1e5b\\u1e6d\\u1e92\\u1eca\\u1ecb\\u1ecc\\u1ecd\\u1ee4\\u1ee5\\u2116",
                                "\\u0207\\u0194\\u0263\\u0283\\u2047\\u01f1\\u01f2\\u01f3\\u01c4\\u01c5\\u01c6\\u01c7\\u01c8\\u01ca\\u01cb\\u01cc",
                                "\\u2139\\u1d6b\\ua732\\ua733\\ua734\\ua735\\ua736\\ua737\\ua738\\ua73a\\ua73c\\ua73d\\ua74e\\ua74f\\ua760\\ua761",
                                "\\ufb04\\ufb06\\u16a1\\u16b5\\u01a0\\u01a1\\u01af\\u01b0\\u1eae\\u1eaf\\u1ea4\\u1ea5\\u1ebe\\u1ebf\\u1ed1\\u1eda",
                                "\\u1edb\\u1ee8\\u1ee9\\u1eb0\\u1eb1\\u1ea6\\u1ea7\\u1ec0\\u1ec1\\u1ed3\\u1edc\\u1edd\\u1eea\\u1eeb\\u1ea2\\u1ea3",
                                "\\u1eb2\\u1eb3\\u1ea8\\u1ea9\\u1eba\\u1ebb\\u1ed5\\u1ede\\u1ec2\\u1ec3\\u1ec8\\u1ec9\\u1ece\\u1ecf\\u1ed4\\u1edf",
                                "\\u1ee6\\u1ee7\\u1eec\\u1eed\\u1ef6\\u1ef7\\u1ea0\\u1ea1\\u1eb6\\u1eb7\\u1eac\\u1ead\\u1eb8\\u1eb9\\u1ec6\\u1ec7",
                                "\\u1ed8\\u1ed9\\u1ee2\\u1ee3\\u1ef0\\u1ef1\\u1ef4\\u1ef5\\u1ed0\\u0195\\u1eaa\\u1eab\\u1ed6\\u1ed7\\u1eef\\u261e",
                                "\\u261c\\u262e\\u1eb4\\u1eb5\\u1ebc\\u1ebd\\u1ec4\\u1ec5\\u1ed2\\u1ee0\\u1ee1\\u1eee\\u1ef8\\u1ef9\\u0498\\u0499",
                                "\\u04a0\\u04a1\\u04aa\\u04ab\\u01f6\\u26a0\\u24ea\\u2460\\u2461\\u2462\\u2463\\u2464\\u2465\\u2466\\u2467\\u2468",
                                "\\u2469\\u246a\\u246b\\u246c\\u246d\\u246e\\u246f\\u2470\\u2471\\u2472\\u2473\\u24b6\\u24b7\\u24b8\\u24b9\\u24ba",
                                "\\u24bb\\u24bc\\u24bd\\u24be\\u24bf\\u24c0\\u24c1\\u24c2\\u24c3\\u24c4\\u24c5\\u24c6\\u24c7\\u24c8\\u24c9\\u24ca",
                                "\\u24cb\\u24cc\\u24cd\\u24ce\\u24cf\\u24d0\\u24d1\\u24d2\\u24d3\\u24d4\\u24d5\\u24d6\\u24d7\\u24d8\\u24d9\\u24da",
                                "\\u24db\\u24dc\\u24dd\\u24de\\u24df\\u24e0\\u24e1\\u24e2\\u24e3\\u24e4\\u24e5\\u24e6\\u24e7\\u24e8\\u24e9\\u0327",
                                "\\u0282\\u0290\\u0276\\u01cd\\u01ce\\u01de\\u01df\\u01fa\\u01fb\\u0202\\u0203\\u0226\\u0227\\u01e0\\u01e1\\u1e00",
                                "\\u1e01\\u0200\\u0201\\u1e06\\u1e07\\u1e04\\u1e05\\u1d6c\\u1e08\\u1e09\\u1e10\\u1e11\\u1e12\\u1e13\\u1e0e\\u1e0f",
                                "\\u1e0c\\u1d6d\\u1e14\\u1e15\\u1e16\\u1e17\\u1e18\\u1e19\\u1e1c\\u1e1d\\u0228\\u0229\\u1e1a\\u1e1b\\u0204\\u0205",
                                "\\u0206\\u1d6e\\u01f4\\u01f5\\u01e6\\u1e26\\u1e27\\u1e28\\u1e29\\u1e2a\\u1e2b\\u021e\\u021f\\u1e24\\u1e96\\u1e2e",
                                "\\u1e2f\\u020a\\u020b\\u01cf\\u01d0\\u0208\\u0209\\u1e2c\\u1e2d\\u01f0\\u0237\\u01e8\\u01e9\\u1e32\\u1e33\\u1e34",
                                "\\u1e35\\u1e3a\\u1e3b\\u1e3c\\u1e3d\\u1e36\\u1e37\\u1e38\\u1e39\\u2c62\\u1e3e\\u1e3f\\u1e42\\u1e43\\u1d6f\\u1e44",
                                "\\u1e45\\u1e46\\u1e47\\u1e4a\\u1e4b\\u01f8\\u01f9\\u1e48\\u1e49\\u1d70\\u01ec\\u01ed\\u022c\\u022d\\u1e4c\\u1e4d",
                                "\\u1e4e\\u1e4f\\u1e50\\u1e51\\u1e52\\u1e53\\u020e\\u020f\\u022a\\u022b\\u01d1\\u01d2\\u022e\\u022f\\u0230\\u0231",
                                "\\u020c\\u020d\\u01ea\\u1e54\\u1e55\\u1d71\\u0212\\u0213\\u1e58\\u1e59\\u1e5c\\u1e5d\\u1e5e\\u1e5f\\u0210\\u0211",
                                "\\u1e5a\\u1d73\\u1d72\\u1e64\\u1e65\\u1e66\\u1e67\\u1e62\\u1e63\\u1e68\\u1e69\\u1d74\\u1e70\\u1e71\\u1e6e\\u1e6f",
                                "\\u1e6c\\u1e97\\u1d75\\u1e72\\u1e73\\u1e76\\u1e77\\u1e78\\u1e79\\u1e7a\\u1e7b\\u01d3\\u01d4\\u01d5\\u01d6\\u01d7",
                                "\\u01d8\\u01d9\\u01da\\u01db\\u01dc\\u1e74\\u1e75\\u0214\\u0215\\u0216\\u1e7e\\u1e7f\\u1e7c\\u1e7d\\u1e86\\u1e87",
                                "\\u1e88\\u1e89\\u1e98\\u1e8c\\u1e8d\\u1e8a\\u1e8b\\u0232\\u0233\\u1e8e\\u1e8f\\u1e99\\u1e94\\u1e95\\u1e90\\u1e91",
                                "\\u1e93\\u1d76\\u01ee\\u01ef\\u1e9b\\ua73e\\ua73f\\u01e2\\u01e3\\u1d7a\\u1efb\\u1d02\\u1d14\\uab63\\u0238\\u02a3",
                                "\\u02a5\\u02a4\\u02a9\\u02aa\\u02ab\\u0239\\u02a8\\u02a6\\u02a7\\uab50\\uab51\\u20a7\\u1efa\\ufb2e\\ufb2f\\u0180",
                                "\\u0182\\u0183\\u0187\\u0188\\u018a\\u018b\\u018c\\u0193\\u01e4\\u01e5\\u0197\\u0196\\u0269\\u0198\\u0199\\u019d",
                                "\\u01a4\\u01a5\\u027d\\u01a6\\u01ac\\u01ad\\u01ab\\u01ae\\u0217\\u01b1\\u019c\\u01b3\\u01b4\\u01b5\\u01b6\\u01a2",
                                "\\u01a3\\u0222\\u0223\\u02ad\\u02ae\\u02af\\ufb14\\ufb15\\ufb17\\ufb16\\ufb13\\u04d0\\u04d1\\u04d2\\u04d3\\u04f6",
                                "\\u04f7\\u0494\\u0495\\u04d6\\u04d7\\u04bc\\u04bd\\u04be\\u04bf\\u04da\\u04db\\u04dc\\u04dd\\u04c1\\u04c2\\u04de",
                                "\\u04df\\u04e2\\u04e3\\u04e4\\u04e5\\u04e6\\u04e7\\u04ea\\u04eb\\u04f0\\u04f1\\u04ee\\u04ef\\u04f2\\u04f3\\u04f4",
                                "\\u04f5\\u04f8\\u04f9\\u04ec\\u04ed\\u0476\\u0477\\u04d4\\u04fa\\u0502\\ua682\\ua680\\ua688\\u052a\\u052c\\ua684",
                                "\\u0504\\u0510\\u04e0\\u0506\\u048a\\u04c3\\u049e\\u049c\\u051e\\u051a\\u04c5\\u052e\\u0512\\u0520\\u0508\\u0514",
                                "\\u04cd\\u04c9\\u0528\\u04c7\\u04a4\\u0522\\u050a\\u04a8\\u0524\\u04a6\\u048e\\u0516\\u050c\\ua690\\u04ac\\ua68a",
                                "\\ua68c\\u050e\\u04b2\\u04fc\\u04fe\\u0526\\ua694\\u04b4\\ua68e\\u04b6\\u04cb\\u04b8\\ua692\\ua696\\ua686\\u048c",
                                "\\u0518\\u051c\\u04d5\\u04fb\\u0503\\ua683\\ua681\\ua689\\u052b\\u052d\\ua685\\u0505\\u0511\\u04e1\\u0507\\u048b",
                                "\\u04c4\\u049f\\u049d\\u051f\\u051b\\u04c6\\u052f\\u0513\\u0521\\u0509\\u0515\\u04ce\\u04ca\\u0529\\u04c8\\u04a5",
                                "\\u0523\\u050b\\u04a9\\u0525\\u04a7\\u048f\\u0517\\u050d\\ua691\\u04ad\\ua68b\\ua68d\\u050f\\u04b3\\u04fd\\u04ff",
                                "\\u0527\\ua695\\u04b5\\ua68f\\u04b7\\u04cc\\u04b9\\ua693\\ua697\\ua687\\u048d\\u0519\\u051d\\u1f08\\u1f00\\u1f09",
                                "\\u1f01\\u1f0a\\u1f02\\u1f0b\\u1f03\\u1f0c\\u1f04\\u1f0d\\u1f05\\u1f0e\\u1f06\\u1f0f\\u1f07\\u1fba\\u1f70\\u1fb8",
                                "\\u1fb0\\u1fb9\\u1fb1\\u1fbb\\u1f71\\u1f88\\u1f80\\u1f89\\u1f81\\u1f8a\\u1f82\\u1f8b\\u1f83\\u1f8c\\u1f84\\u1f8d",
                                "\\u1f85\\u1f8e\\u1f86\\u1f8f\\u1f87\\u1fbc\\u1fb4\\u1fb6\\u1fb7\\u1fb2\\u1fb3\\u1f18\\u1f10\\u1f19\\u1f11\\u1f1a",
                                "\\u1f12\\u1f1b\\u1f13\\u1f1c\\u1f14\\u1f1d\\u1f15\\u1fc8\\u1fc9\\u1f72\\u1f73\\u1f28\\u1f20\\u1fca\\u1f74\\u1f29",
                                "\\u1f21\\u1f2a\\u1f22\\u1f2b\\u1f23\\u1f2c\\u1f24\\u1f2d\\u1f25\\u1f2e\\u1f26\\u1f2f\\u1f27\\u1f98\\u1f90\\u1f99",
                                "\\u1f91\\u1f9a\\u1f92\\u1f9b\\u1f93\\u1f9c\\u1f94\\u1f9d\\u1f95\\u1f9e\\u1f96\\u1f9f\\u1f97\\u1fcb\\u1f75\\u1fcc",
                                "\\u1fc3\\u1fc2\\u1fc4\\u1fc6\\u1fc7\\u1fda\\u1f76\\u1fdb\\u1f77\\u1f38\\u1f30\\u1f39\\u1f31\\u1f3a\\u1f32\\u1f3b",
                                "\\u1f33\\u1f3c\\u1f34\\u1f3d\\u1f35\\u1f3e\\u1f36\\u1f3f\\u1f37\\u1fd8\\u1fd0\\u1fd9\\u1fd1\\u1fd2\\u1fd3\\u1fd6",
                                "\\u1fd7\\u1ff8\\u1f78\\u1ff9\\u1f79\\u1f48\\u1f40\\u1f49\\u1f41\\u1f4a\\u1f42\\u1f4b\\u1f43\\u1f4c\\u1f44\\u1f4d",
                                "\\u1f45\\u1fec\\u1fe4\\u1fe5\\u1fea\\u1f7a\\u1feb\\u1f7b\\u1f59\\u1f51\\u1f5b\\u1f53\\u1f5d\\u1f55\\u1f5f\\u1f57",
                                "\\u1fe8\\u1fe0\\u1fe9\\u1fe1\\u03d3\\u03d4\\u1fe2\\u1fe3\\u1fe7\\u1f50\\u1f52\\u1f54\\u1fe6\\u1f56\\u1ffa\\u1f7c",
                                "\\u1ffb\\u1f7d\\u1f68\\u1f60\\u1f69\\u1f61\\u1f6a\\u1f62\\u1f6b\\u1f63\\u1f6c\\u1f64\\u1f6d\\u1f65\\u1f6e\\u1f66",
                                "\\u1f6f\\u1f67\\u1fa8\\u1fa0\\u1fa9\\u1fa1\\u1faa\\u1fa2\\u1fab\\u1fa3\\u1fac\\u1fa4\\u1fad\\u1fa5\\u1fae\\u1fa6",
                                "\\u1faf\\u1fa7\\u1ffc\\u1ff3\\u1ff2\\u1ff4\\u1ff6\\u1ff7\\u262f\\u2610\\u2611\\u2612\\u018d\\u01ba\\u2c7e\\u023f",
                                "\\u2c7f\\u0240\\u1d80\\ua7c4\\ua794\\u1d81\\u1d82\\u1d83\\ua795\\u1d84\\u1d85\\u1d86\\u1d87\\u1d88\\u1d89\\u1d8a",
                                "\\u1d8b\\u1d8c\\u1d8d\\ua7c6\\u1d8e\\u1d8f\\u1d90\\u1d92\\u1d93\\u1d94\\u1d95\\u1d96\\u1d97\\u1d98\\u1d99\\u1d9a",
                                "\\u1e9a\\u2152\\u2158\\u20a8\\u20af\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000"
                            ]
                        },
                        {
                            "type": "bitmap",
                            "file": "minecraft:font/ascii.png",
                            "ascent": %s,
                            "chars": [
                                "\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000",
                                "\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000",
                                "\\u0020\\u0021\\u0022\\u0023\\u0024\\u0025\\u0026\\u0027\\u0028\\u0029\\u002a\\u002b\\u002c\\u002d\\u002e\\u002f",
                                "\\u0030\\u0031\\u0032\\u0033\\u0034\\u0035\\u0036\\u0037\\u0038\\u0039\\u003a\\u003b\\u003c\\u003d\\u003e\\u003f",
                                "\\u0040\\u0041\\u0042\\u0043\\u0044\\u0045\\u0046\\u0047\\u0048\\u0049\\u004a\\u004b\\u004c\\u004d\\u004e\\u004f",
                                "\\u0050\\u0051\\u0052\\u0053\\u0054\\u0055\\u0056\\u0057\\u0058\\u0059\\u005a\\u005b\\u005c\\u005d\\u005e\\u005f",
                                "\\u0060\\u0061\\u0062\\u0063\\u0064\\u0065\\u0066\\u0067\\u0068\\u0069\\u006a\\u006b\\u006c\\u006d\\u006e\\u006f",
                                "\\u0070\\u0071\\u0072\\u0073\\u0074\\u0075\\u0076\\u0077\\u0078\\u0079\\u007a\\u007b\\u007c\\u007d\\u007e\\u0000",
                                "\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000",
                                "\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u00a3\\u0000\\u0000\\u0192",
                                "\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u00aa\\u00ba\\u0000\\u0000\\u00ac\\u0000\\u0000\\u0000\\u00ab\\u00bb",
                                "\\u2591\\u2592\\u2593\\u2502\\u2524\\u2561\\u2562\\u2556\\u2555\\u2563\\u2551\\u2557\\u255d\\u255c\\u255b\\u2510",
                                "\\u2514\\u2534\\u252c\\u251c\\u2500\\u253c\\u255e\\u255f\\u255a\\u2554\\u2569\\u2566\\u2560\\u2550\\u256c\\u2567",
                                "\\u2568\\u2564\\u2565\\u2559\\u2558\\u2552\\u2553\\u256b\\u256a\\u2518\\u250c\\u2588\\u2584\\u258c\\u2590\\u2580",
                                "\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u0000\\u2205\\u2208\\u0000",
                                "\\u2261\\u00b1\\u2265\\u2264\\u2320\\u2321\\u00f7\\u2248\\u00b0\\u2219\\u0000\\u221a\\u207f\\u00b2\\u25a0\\u0000"
                            ]
                        },
                        {
                            "type": "reference",
                            "id": "minecraft:include/unifont"
                        }
                    ]
                }
                """, ascent, ascent + 3, ascent);

        this.identifier = identifier;

        resources.add(this);
    }

    public MutableText asText(Text text) {
        return ((MutableText) text).styled(style -> style.withFont(this.identifier));
    }
}