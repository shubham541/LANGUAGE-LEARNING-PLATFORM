import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'language'
})
export class LanguagePipe implements PipeTransform {

  // converts 2 letter language code to full language name
  transform(value: string, ...args: unknown[]): unknown {
    const languageNames = new Intl.DisplayNames(['en'], {
      type: 'language'
    });
    return languageNames.of(value);
  }

}
