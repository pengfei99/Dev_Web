package org.etriks.layout


class MenuTagLib
{
    static defaultEncodeAs = 'html'
    //static encodeAsForTags = [tagName: 'raw']
    static namespace = 'mx'

    /** The toolbar container */
    def toolbar =
            { attrs, body ->
                def separator = attrs.separator ?: '|'

                // we'll be using this to keep track of our items
                def menuItems = []
                this.pageScope.menuItems = menuItems

                // this will execute the body, so that the items are appended obviously,
                // anything other than the item tags will not render correctly.
                body()

                // render every item and add a separator except for the last item
                menuItems.eachWithIndex
                        { item, index ->
                            out << item
                            if(index < menuItems.size() - 1)
                                out << separator
                        }
            }

    /** A toolbar item */
    def item =
            { attrs, body ->
                def itemContent = body().trim()
                if(itemContent) // only add the item if rendering evaluated to non-empty string
                    this.pageScope.menuItems << itemContent
            }
}

