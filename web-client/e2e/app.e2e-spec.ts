import { EasyDevPage } from './app.po';

describe('easy-dev App', () => {
  let page: EasyDevPage;

  beforeEach(() => {
    page = new EasyDevPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
