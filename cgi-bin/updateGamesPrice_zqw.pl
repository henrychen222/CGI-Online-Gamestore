#!/usr/bin/perl
use CGI;
$query  = new CGI;

$asin  = $query->param( 'asin' );
$price  = $query->param( 'price' );



  # Remove leading and trailing spacing.
  $username =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $username =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $password =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $password =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom updateGamesPrice_zqw";
$cmd   .= " '". $asin . "'";
$cmd   .= " '". $price . "'";

#print( $cmd );
system( $cmd );



