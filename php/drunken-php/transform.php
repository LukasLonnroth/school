<?php
/**
 * Created by PhpStorm.
 * User: Andrej
 * Date: 11/01/18
 * Edited: Lukas
 * 13/01/2018
 */

$substantiv =
    [
        'grunderna',
        'programmering',
        'programmeringsspråk',
        'programkod',
        'dataprogram',
        'PHP',
        'indata',
        'utdata',
        'algoritm',
        'algoritmer',
        'lösningen',
        'problemlösning',
        'delproblem',
        'programmeringsprocessen',
        'upprepningen',
        'spel',
        'navigationsprogram',
        'inbandningsprogram',
        'webbsidor',
        'webbserver',
        'mobilappar',
        'webbapplikationer',
        'exempel',
        'dator',
        'datorer',
        'bordsdatorer',
        'laptops',
        'mobiltelefoner',
        'bilar',
        'robotar',
        'räknemaskiner',
        'maskin',
        'industrin',
        'instruktioner',
        'instruktionerna',
        'sätt',
        'språk',
        'verkligheten',
        'artikeln',
        'tillgång',
        'ordning',
        'testning',
        'felsökning',
        'villkorssatser',
        'alternativ',
        'upprepningssats',
        'rader',
        'resultat',
        'resultatet',
        'metoderna',
        'information',
        'nivå',
        'kaka',
        'kakor',
        'gäster',
        'festen',
        'personer',
        'tecken',
        'iteration',
        'operator',
        'sekvens',
        'selektion',
        'variabel',
        'botten',
        'grund',
        'tagg'
    ];

$adjektiv = [
    'ärlig',
    'otålig',
    'farlig',
    'händig',
    'intelligent',
    'hjälpsam',
    'generös',
    'noggrann',
    'sportig',
    'pålitlig',
    'religiös',
    'pratsam',
    'utåtriktad',
    'äventyrlig',
    'obekväm',
    'laglig',
    'fattig',
    'nyttig',
    'hemsk',
    'slarvig',
    'pytteliten',
    'varm',
    'tjock',
    'lång',
    'trött',
    'nyttig',
    'adlig',
    'aggressiv',
    'aktiv',
    'alkoholfri',
    'ambitiös',
    'alternativ',
    'allvarsam',
    'anal',
    'allsvensk',
    'allergisk',
    'andfådd',
    'anekdotisk',
    'anglosaxisk',
    'animalisk',
    'anorektisk',
    'ansvarsfull',
    'antastande',
    'antisocial',
    'anträffbar',
    'användarvänlig',
    'apatisk',
    'apostolisk',
    'arbetsfri',
    'argsint',
    'arkaisk',
    'aromatisk',
    'arrogant',
    'artig',
    'asberusad',
    'asexuell',
    'asocial',
    'astmatisk',
    'ateistisk',
    'attackerande',
    'autistisk',
    'avdankad',
    'avgiftsfri',
    'avgudad',
    'avkopplande',
    'avkönad',
    'avlidande',
    'avlägsen',
    'avlösande',
    'avreagerande',
    'avskalad',
    'avskedad',
    'avskriven',
    'avslappnad',
    'avslöjad',
    'avsomnad',
    'avundsjuk'
];

$text = file_get_contents('vm.html');

// skriv din kod här, t.ex., så att innehållet i $text förändras
for($i = 1; $i < count($substantiv); $i++){
    if(strpos($text, $substantiv[$i])){
        $randIndex = array_rand($adjektiv);
        $randAdjektiv = $adjektiv[$randIndex];
        $subAndAdj = $randAdjektiv.' '.$substantiv[$i];
        $text = str_replace($substantiv[$i],$subAndAdj,$text);
    }
}



echo $text;

?>
