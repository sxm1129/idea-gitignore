/*
 * The MIT License (MIT)
 *
 * Copyright (c) today.year hsz Jakub Chrzanowski <jakub@hsz.mobi>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mobi.hsz.idea.gitignore.highlighter;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import mobi.hsz.idea.gitignore.GitignoreBundle;
import mobi.hsz.idea.gitignore.GitignoreLanguage;
import mobi.hsz.idea.gitignore.util.Icons;
import mobi.hsz.idea.gitignore.util.Resources;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * {@link ColorSettingsPage} that allows to modify color scheme.
 *
 * @author Jakub Chrzanowski <jakub@hsz.mobi>
 * @since 0.1
 */
public class GitignoreColorSettingsPage implements ColorSettingsPage {

    /** The path to the sample .gitignore file */
    @NonNls
    private static final String SAMPLE_GITIGNORE_PATH = "/sample.gitignore";

    /**
     * The sample .gitignore document shown in the colors settings dialog.
     *
     * @see #loadSampleGitignore()
     */
    private static final String SAMPLE_GITIGNORE = loadSampleGitignore();

    /**  Attributes descriptor list. */
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor(GitignoreBundle.message("highlighter.header"), GitignoreHighlighterColors.HEADER_ATTR_KEY),
            new AttributesDescriptor(GitignoreBundle.message("highlighter.section"), GitignoreHighlighterColors.SECTION_ATTR_KEY),
            new AttributesDescriptor(GitignoreBundle.message("highlighter.comment"), GitignoreHighlighterColors.COMMENT_ATTR_KEY),
            new AttributesDescriptor(GitignoreBundle.message("highlighter.negation"), GitignoreHighlighterColors.NEGATION_ATTR_KEY),
            new AttributesDescriptor(GitignoreBundle.message("highlighter.brackets"), GitignoreHighlighterColors.BRACKET_ATTR_KEY),
            new AttributesDescriptor(GitignoreBundle.message("highlighter.slash"), GitignoreHighlighterColors.SLASH_ATTR_KEY),
            new AttributesDescriptor(GitignoreBundle.message("highlighter.value"), GitignoreHighlighterColors.VALUE_ATTR_KEY),
    };

    /**
     * Returns the icon for the page, shown in the dialog tab.
     *
     * @return the icon for the page, or null if the page does not have a custom icon.
     */
    @Nullable
    @Override
    public Icon getIcon() {
        return Icons.FILE;
    }

    /**
     * Returns the syntax highlighter which is used to highlight the text shown in the preview
     * pane of the page.
     *
     * @return the syntax highlighter instance.
     */
    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new GitignoreHighlighter(null, null);
    }

    /**
     * Returns the text shown in the preview pane.
     *
     * @return demo text
     */
    @NotNull
    @Override
    public String getDemoText() {
        return SAMPLE_GITIGNORE;
    }

    /**
     * Returns the mapping from special tag names surrounding the regions to be highlighted
     * in the preview text.
     *
     * @return <code>null</code>
     */
    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    /**
     * Returns the list of descriptors specifying the {@link TextAttributesKey} instances
     * for which colors are specified in the page. For such attribute keys, the user can choose
     * all highlighting attributes (font type, background color, foreground color, error stripe color and
     * effects).
     *
     * @return the list of attribute descriptors.
     */
    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    /**
     * Returns the list of descriptors specifying the {@link com.intellij.openapi.editor.colors.ColorKey}
     * instances for which colors are specified in the page. For such color keys, the user can
     * choose only the background or foreground color.
     *
     * @return the list of color descriptors.
     */
    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    /**
     * Returns the title of the page, shown as text in the dialog tab.
     *
     * @return the title of the custom page.
     */
    @NotNull
    @Override
    public String getDisplayName() {
        return GitignoreLanguage.NAME;
    }

    /**
     * Loads sample .gitignore file
     *
     * @return the text loaded from {@link #SAMPLE_GITIGNORE_PATH}
     * @see #getDemoText()
     * @see #SAMPLE_GITIGNORE_PATH
     * @see #SAMPLE_GITIGNORE
     */
    private static String loadSampleGitignore() {
        return Resources.getResourceContent(SAMPLE_GITIGNORE_PATH);
    }
}
